package com.BrrowingServices.BrrowingService.Command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
public class UpdateBookStatusCommand {
    @TargetAggregateIdentifier
    private  String bookId ;
    private  Boolean isReady ;
    private String employeeId ;
    private  String borrowId ;
}
