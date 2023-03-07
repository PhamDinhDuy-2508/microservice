package com.BookService.BookService.DTO;

import lombok.Data;

public class BookDTO {
    private  String id ;
    private  String author ;
    private  String name ;
    private  Boolean isread ;

    public BookDTO(String id, String author, String name, Boolean isread) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.isread = isread;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }
}
