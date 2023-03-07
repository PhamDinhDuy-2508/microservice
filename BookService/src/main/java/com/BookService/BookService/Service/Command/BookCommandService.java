package com.BookService.BookService.Service.Command;

import com.BookService.BookService.DTO.BookDTO;
import com.BookService.BookService.DTO.BookUpdateStatusDTO;
import com.BookService.BookService.DTO.Book_Del_DTO;

public interface BookCommandService {
    void AddBook(BookDTO bookDTO);

    Boolean DeleteBook(Book_Del_DTO del_dto);

    Boolean UpdateBook(BookDTO bookDTO);

    Boolean UpdateStatusBook(BookUpdateStatusDTO bookUpdateStatusDTO);

}
