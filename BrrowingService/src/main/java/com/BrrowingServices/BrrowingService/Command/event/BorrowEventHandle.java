package com.BrrowingServices.BrrowingService.Command.event;

import com.BrrowingServices.BrrowingService.Command.data.Borrow;
import com.BrrowingServices.BrrowingService.Command.data.Respository.BorrowRepository;
import com.BrrowingServices.BrrowingService.service.SendSMessageService;
import org.axonframework.eventhandling.EventHandler;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Component
public class BorrowEventHandle {
    @Autowired
    BorrowRepository borrowRepository ;
    @Autowired
    RestTemplate restTemplate ;

    @EventHandler
    @Transactional
    public void CreateBorrowRequest(BorrowCreatedEvent borrowCreatedEvent) {
        Borrow borrow =  new Borrow() ;
        BeanUtils.copyProperties(borrowCreatedEvent ,  borrow);
        borrowRepository.save(borrow) ;
    }

    @EventHandler
    @Transactional
    public void DeleteBorrowRequest(BorrowEventDelete borrowEventDelete) {
        Borrow borrow =  borrowRepository.findById(borrowEventDelete.getId()) ;
        BeanUtils.copyProperties(borrowEventDelete , borrow);
        borrowRepository.delete(borrow);
    }
    @EventHandler
    @Async
    public void SendMessageRequest(SendMessageEvent sendMessageEvent) {
//        SendSMessageService sendSMessageService =  new SendSMessageService() ;
//        BeanUtils.copyProperties(sendMessageEvent , sendSMessageService);
    }
    @EventHandler
    @Async
    public void SendMessageUpdateBookStatus(SendMessageBookEvent sendMessageBookEvent) {
        SendSMessageService sendSMessageService =  new SendSMessageService() ;
        sendSMessageService.setTopic("BookUpdateStatus");
        sendSMessageService.setMessage(sendMessageBookEvent.toString());
    }
}
