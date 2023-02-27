package com.BookService.BookService.Command.Service;

import com.BookService.BookService.Command.command.UpdateStatusCommand;
import com.BookService.BookService.Command.data.Respository.BookRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {
    @Autowired
    BookRespository bookRespository  ;

    @Async
    @Transactional
    public void UpdateStatus(UpdateStatusCommand updateStatusCommand) {
        try {
            StringBuilder stringBuilder =  new StringBuilder(updateStatusCommand.getBookId()) ;
            bookRespository.updateStatus(updateStatusCommand.isIsread(),stringBuilder.toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
