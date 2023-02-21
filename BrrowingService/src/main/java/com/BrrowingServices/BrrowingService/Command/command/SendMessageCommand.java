package com.BrrowingServices.BrrowingService.Command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
public class SendMessageCommand
{
    private  String Message ;
    @TargetAggregateIdentifier
    private  String Id ;

    public SendMessageCommand(String message, String id) {
        Message = message;
        Id = id;
    }
}
