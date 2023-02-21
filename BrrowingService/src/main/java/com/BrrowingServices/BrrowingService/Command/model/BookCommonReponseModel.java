package com.BrrowingServices.BrrowingService.Command.model;

import lombok.Data;

@Data
public class BookCommonReponseModel {
    private  String bookId ;
    private  String  name ;
    private  String author ;
    private  Boolean isReady ;

}
