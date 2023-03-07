package com.BookService.BookService.Service.Query;

import com.BookService.BookService.DTO.BookResponse;

import java.util.List;

public interface BookQueryService {
    BookResponse getBookById(String id) ;
    List<BookResponse> getAllBook() ;
}
