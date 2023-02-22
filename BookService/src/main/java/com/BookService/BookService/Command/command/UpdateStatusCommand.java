package com.BookService.BookService.Command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
public class UpdateStatusCommand {
    @TargetAggregateIdentifier
    private  String bookId ;
    private  boolean Isread ;
}
