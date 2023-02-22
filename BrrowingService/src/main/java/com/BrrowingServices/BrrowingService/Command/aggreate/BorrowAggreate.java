package com.BrrowingServices.BrrowingService.Command.aggreate;

import com.BrrowingServices.BrrowingService.Command.command.BorroqwDeletedCommand;
import com.BrrowingServices.BrrowingService.Command.command.BorrowCreatedCommand;
import com.BrrowingServices.BrrowingService.Command.command.SendMessageCommand;
import com.BrrowingServices.BrrowingService.Command.command.UpdateBookStatusCommand;
import com.BrrowingServices.BrrowingService.Command.event.BorrowCreatedEvent;
import com.BrrowingServices.BrrowingService.Command.event.BorrowEventDelete;
import com.BrrowingServices.BrrowingService.Command.event.SendMessageBookEvent;
import com.BrrowingServices.BrrowingService.Command.event.SendMessageEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class BorrowAggreate {
    @AggregateIdentifier
    private String id;
    private String BookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
    private  String message ;

    public BorrowAggreate() {
    }

    @CommandHandler
    public BorrowAggreate(BorrowCreatedCommand borrowCreatedCommand) {
        BorrowCreatedEvent borrowCreatedEvent = new BorrowCreatedEvent();
        BeanUtils.copyProperties(borrowCreatedCommand, borrowCreatedEvent);
        AggregateLifecycle.apply(borrowCreatedEvent);
    }

    @CommandHandler
    public void on(BorroqwDeletedCommand borroqwDeletedCommand) {
        BorrowEventDelete borrowEventDelete = new BorrowEventDelete();
        BeanUtils.copyProperties( borroqwDeletedCommand, borrowEventDelete);
        AggregateLifecycle.apply(borrowEventDelete);
    }
    @CommandHandler
    public void on (SendMessageCommand sendMessageCommand) {
        SendMessageEvent sendMessageEvent =  new SendMessageEvent() ;
        BeanUtils.copyProperties(sendMessageCommand  ,  sendMessageEvent);
        AggregateLifecycle.apply(sendMessageEvent) ;
    }
    @CommandHandler
    public void on(UpdateBookStatusCommand updateBookStatusCommand) {
        SendMessageBookEvent sendMessageBookEvent =  new SendMessageBookEvent() ;
        BeanUtils.copyProperties(updateBookStatusCommand , sendMessageBookEvent);
        AggregateLifecycle.apply(sendMessageBookEvent) ;
    }

    @EventSourcingHandler
    public void handle(BorrowCreatedEvent event) {
        this.BookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = event.getBorrowingDate();
        this.returnDate = event.getBorrowingDate();
    }

    @EventSourcingHandler
    public void handle(BorrowEventDelete event) {
        this.BookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = event.getBorrowingDate();
        this.returnDate = event.getBorrowingDate();
    }

    @EventSourcingHandler
    public  void handle(SendMessageEvent sendMessageEvent) {
        this.message =  sendMessageEvent.getMessage() ;
    }





}
