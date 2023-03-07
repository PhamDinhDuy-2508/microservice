package com.BookService.BookService.DTO;

import lombok.Data;

@Data
public class BookResponse {
    private  String Id ;
    private  String Author ;
    private  String Name ;
    private  boolean Isread ;
}
