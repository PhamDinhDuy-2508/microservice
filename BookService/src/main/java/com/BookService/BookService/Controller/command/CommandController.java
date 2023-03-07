package com.BookService.BookService.Controller.command;

import com.BookService.BookService.DTO.BookDTO;
import com.BookService.BookService.DTO.BookUpdateStatusDTO;
import com.BookService.BookService.DTO.Book_Del_DTO;
import com.BookService.BookService.Service.Command.BookCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/v1/book")
@RestController
public class CommandController {

    @Autowired
    BookCommandService bookCommandService ;
    @PostMapping("/addBook")
    public void AddBook(@RequestBody BookDTO bookDTO) {
        bookCommandService.AddBook(bookDTO);
    }

    @DeleteMapping("/Delete")
    public CompletableFuture<?> Delete(@RequestBody Book_Del_DTO book_del_dto) {
      return CompletableFuture.supplyAsync(() -> {
          return bookCommandService.DeleteBook(book_del_dto);
      }).thenApply( t ->{
          if(t) {
              return ResponseEntity.ok("Xoa sach thanh cong") ;
          }
          else {
              return ResponseEntity.ok("Co loi xay ra") ;
          }
      });
    }

    @PutMapping("/UpdateBookModel")
    public CompletableFuture<?> Delete(@RequestBody BookDTO bookDTO) {

        return CompletableFuture.supplyAsync(() -> {
            return bookCommandService.UpdateBook(bookDTO);
        }).thenApply( t ->{
            if(t) {
                return ResponseEntity.ok("Cap Nhat sach thanh cong") ;
            }
            else {
                return ResponseEntity.ok("Co loi xay ra") ;
            }
        });
    }

    @KafkaListener(topics = "BookUpdateStatus", groupId = "json")
    public CompletableFuture<?> UpdateStatus(@RequestBody BookUpdateStatusDTO bookUpdateStatusDTO) {
        return  CompletableFuture.supplyAsync(()->{
            return  bookCommandService.UpdateStatusBook(bookUpdateStatusDTO) ;
        }).thenApply(t->{
            if(t) {
                return ResponseEntity.ok("Cap Nhat sach thanh cong") ;
            }
            else {
                return ResponseEntity.ok("Co loi xay ra") ;
            }
        }) ;
    }


}
