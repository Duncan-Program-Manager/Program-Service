package com.dpm.program.rabbitmq;

import com.dpm.program.dto.ProgramDTO;
import com.dpm.program.dto.ProgramRecieveDTO;
import com.dpm.program.service.ProgramService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class virusScannerReciever implements MessageListener {

    @Autowired
    private ProgramService programService;

    public void onMessage(Message message) {
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

            JSONObject jsonObject = (JSONObject) json.get("data");
            System.out.println(jsonObject);
            Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();

            ProgramRecieveDTO dto = gson.fromJson(jsonObject.toJSONString(), ProgramRecieveDTO.class);
            try {
                programService.UploadProgram(dto, dto.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
