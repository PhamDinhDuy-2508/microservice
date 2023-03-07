package com.BookService.BookService.Service.Query;

import com.BookService.BookService.DTO.BookResponse;
import com.BookService.BookService.Model.Book;
import com.BookService.BookService.repository.BookRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookQueryServiceImpl implements BookQueryService {
    @Autowired
    private BookRespository bookRespository;

    @Override
    public BookResponse getBookById(String id) {
        BookResponse bookResponse = new BookResponse();
        Book book = bookRespository.getBookById(id);
        BeanUtils.copyProperties(book, bookResponse);
        return bookResponse;
    }

    @Override
    public List<BookResponse> getAllBook() {
        List<Book> bookList = bookRespository.findAll();
        List<BookResponse> bookResponseList = new ArrayList<>();
        bookList.parallelStream().forEach(s -> {
            BookResponse book = new BookResponse();
            BeanUtils.copyProperties(s, book);
            bookResponseList.add(book);
        });
        return bookResponseList;
    }
}
