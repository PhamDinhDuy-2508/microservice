package com.BookService.BookService.Service.Command;

import com.BookService.BookService.DTO.BookDTO;
import com.BookService.BookService.DTO.BookUpdateStatusDTO;
import com.BookService.BookService.DTO.Book_Del_DTO;
import com.BookService.BookService.Model.Book;
import com.BookService.BookService.repository.BookRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookCommandServiecImpl implements BookCommandService {
    @Autowired
    BookRespository bookRespository;

    @Override
    @Async
    public void AddBook(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        bookRespository.save(book);
    }

    @Override
    public Boolean DeleteBook(Book_Del_DTO del_dto) {
        try {
            bookRespository.BookDeleteById(del_dto.getBookId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean UpdateBook(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        bookRespository.save(book);
        return true;
    }

    @Override
    @Transactional
    public Boolean UpdateStatusBook(BookUpdateStatusDTO bookUpdateStatusDTO) {
        try {
            bookRespository.UpdateBook(bookUpdateStatusDTO.getBookId(), bookUpdateStatusDTO.getIsRead());
            return  true ;
        }
        catch (Exception e) {
            return  false ;
        }
    }

}
