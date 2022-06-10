package com.dpm.program.rabbitmq;

import com.dpm.program.dto.ProgramDTO;
import com.dpm.program.service.ProgramService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class userReciever implements MessageListener {

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
        if(json.get("method").equals("deleteUser"))
        {
            JSONObject userInfo = (JSONObject) json.get("data");
            programService.deleteUserDataFromPrograms(userInfo.get("username").toString());
        }

    }
}
