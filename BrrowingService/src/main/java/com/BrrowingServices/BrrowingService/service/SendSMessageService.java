package com.BrrowingServices.BrrowingService.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Data
public class SendSMessageService {
    private String message;
    private String Id;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    public void SendMessage() {
        try {
            kafkaTemplate.send("phamduy" ,  Id + message );
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }

    }



}
