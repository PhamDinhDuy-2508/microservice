package com.BrrowingServices.BrrowingService.service;

import com.BrrowingServices.BrrowingService.Command.event.SendMessageBookEvent;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendBookUpdateStatus {
    @Autowired
    SendSMessageService sendSMessageService ;
    @Async
    public void SendMessage(Object object, String topic){
        Gson gson =  new Gson() ;
        String json =  new String() ;
        json = gson.toJson(object) ;

        sendSMessageService.setTopic(topic);
        sendSMessageService.setMessage(json);
        sendSMessageService.SendMessage();
    }

}
