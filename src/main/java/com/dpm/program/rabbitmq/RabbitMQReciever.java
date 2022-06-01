package com.dpm.program.rabbitmq;

import com.dpm.program.dto.ProgramDTO;
import com.dpm.program.service.ProgramService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReciever implements RabbitListenerConfigurer {

    @Autowired
    private ProgramService programService;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }
    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receivedMessage(Message message) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(new String(message.getBody()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        if(json.get("method").equals("newProgram"))
        {
            JSONObject jsonObject = (JSONObject) json.get("dto");
            ObjectMapper mapper = new ObjectMapper();
            try {
                ProgramDTO dto = mapper.readValue(jsonObject.toString(), ProgramDTO.class);
                programService.UploadProgram(dto);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }
}
