package com.BrrowingServices.BrrowingService.Command.aggreate;

import com.BrrowingServices.BrrowingService.Command.command.UpdateBookStatusCommand;
import com.BrrowingServices.BrrowingService.Command.event.SendMessageBookEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class BookAggreate {
    @AggregateIdentifier
    private String bookId;
    private Boolean isReady;
    private String employeeId;
    private String borrowId;

    @CommandHandler
    public void on(UpdateBookStatusCommand updateBookStatusCommand) {
        SendMessageBookEvent sendMessageBookEvent = new SendMessageBookEvent();
        BeanUtils.copyProperties(updateBookStatusCommand, sendMessageBookEvent);
        AggregateLifecycle.apply(sendMessageBookEvent);
    }

    @EventSourcingHandler
    public void handle(SendMessageBookEvent event) {
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowId = event.getBorrowId() ;
        this.isReady  = event.getIsReady() ;
    }
}
