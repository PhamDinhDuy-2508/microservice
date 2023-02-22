package com.BrrowingServices.BrrowingService.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Data
public class SendSMessageService {
    private String message;

    private  String topic ;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public KafkaTemplate<String, String> getKafkaTemplate() {
        return kafkaTemplate;
    }

    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void SendMessage() {
        try {
            kafkaTemplate.send(this.topic ,  this.message);
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }

    }



}
