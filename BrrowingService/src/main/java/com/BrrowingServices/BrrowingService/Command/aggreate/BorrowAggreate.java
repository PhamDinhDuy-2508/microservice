package com.BrrowingServices.BrrowingService.Command.aggreate;

import com.BrrowingServices.BrrowingService.Command.command.BorroqwDeletedCommand;
import com.BrrowingServices.BrrowingService.Command.command.BorrowCreatedCommand;
import com.BrrowingServices.BrrowingService.Command.command.SendMessageCommand;
import com.BrrowingServices.BrrowingService.Command.command.UpdateBookStatusCommand;
import com.BrrowingServices.BrrowingService.Command.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Aggregate
public class BorrowAggreate {

    @AggregateIdentifier
    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
    private String message;

    public BorrowAggreate() {
    }
    @CommandHandler
    public void on(UpdateBookStatusCommand updateBookStatusCommand) {
        SendMessageBookEvent sendMessageBookEvent = new SendMessageBookEvent();
        BeanUtils.copyProperties(updateBookStatusCommand, sendMessageBookEvent);
        AggregateLifecycle.apply(sendMessageBookEvent);
    }

    @CommandHandler
    public BorrowAggreate(BorrowCreatedCommand borrowCreatedCommand) {
        BorrowCreatedEvent borrowCreatedEvent = new BorrowCreatedEvent();
        BeanUtils.copyProperties(borrowCreatedCommand, borrowCreatedEvent);
        AggregateLifecycle.apply(borrowCreatedEvent);
    }

    @CommandHandler
    public void BorrowAggreate(BorroqwDeletedCommand borroqwDeletedCommand) {
        BorrowEventDelete borrowEventDelete = new BorrowEventDelete();
        BeanUtils.copyProperties(borroqwDeletedCommand, borrowEventDelete);
        AggregateLifecycle.apply(borrowEventDelete);
    }

    @CommandHandler
    public void BorrowAggreate(SendMessageCommand sendMessageCommand) {
        SendMessageEvent sendMessageEvent = new SendMessageEvent();
        BeanUtils.copyProperties(sendMessageCommand, sendMessageEvent);
        AggregateLifecycle.apply(sendMessageEvent);
    }



    @EventSourcingHandler
    public void handle(BorrowCreatedEvent event) {
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = event.getBorrowingDate();
        this.returnDate = event.getBorrowingDate();
        this.id = event.getId();
        this.message = "";
    }

    @EventSourcingHandler
    public void handle(BorrowEventDelete event) {
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = event.getBorrowingDate();
        this.returnDate = event.getBorrowingDate();
    }

    @EventSourcingHandler
    public void handle(SendMessageEvent sendMessageEvent) {
        this.id = sendMessageEvent.getId();
        this.message = "";
    }

    @EventSourcingHandler
    public void handle(SendMessageBookEvent event) {
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = new Date() ;
        this.returnDate = new Date();
        this.id = "";
        this.message = "";
    }


}
