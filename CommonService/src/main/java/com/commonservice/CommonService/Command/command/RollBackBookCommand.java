package com.commonservice.CommonService.Command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
public class RollBackBookCommand
{
    @TargetAggregateIdentifier
    private  String bookId ;
    private  Boolean isReady ;
    private String employeeId ;
    private  String borrowId ;
}
