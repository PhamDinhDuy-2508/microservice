package com.commonservice.CommonService.Command.event;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class BookUpdateCommonEvent {
    private  String bookId ;
    private  Boolean isReady ;
    private String employeeId ;
    private  String borrowId ;
}
