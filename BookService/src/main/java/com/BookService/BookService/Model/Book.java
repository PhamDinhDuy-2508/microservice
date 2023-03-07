package com.BookService.BookService.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "book")
@Entity
public class Book {
    @Id
    private  String Id ;
    private  String Author ;
    private  String Name ;
    private  boolean Isread ;

}
