package com.BookService.BookService.Command.event;

import lombok.Data;

@Data
public class BookUpdateStatusEvent {
    private  String bookId ;
    private  boolean Isread ;

}
