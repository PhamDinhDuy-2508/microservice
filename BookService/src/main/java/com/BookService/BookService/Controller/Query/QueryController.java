package com.BookService.BookService.Controller.Query;

import com.BookService.BookService.DTO.BookResponse;
import com.BookService.BookService.Service.Command.BookCommandService;
import com.BookService.BookService.Service.Query.BookQueryService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/book")
@RestController
public class QueryController {

    @Autowired
    BookQueryService bookQueryService;

    @GetMapping("/getAll")
    public ResponseEntity<?> GetAll() throws Exception {
        List<BookResponse> bookResponseList = new ArrayList<>();
        try {
            bookResponseList = bookQueryService.getAllBook();
            return ResponseEntity.ok(bookResponseList);
        } catch (Exception e) {
            throw new Exception("Khong the lay tat  ca ccac record");
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) throws Exception {
        try {
            BookResponse bookResponse = bookQueryService.getBookById(id);
            return ResponseEntity.ok(bookResponse);
        } catch (Exception e) {
            String error = String.format("Khong tim thay user voi id %id", id);
            throw new Exception(error);
        }
    }

    @GetMapping("/checkIsRead/{id}")
    public Boolean CheckifExist(@PathVariable String id) {
        try {
            BookResponse bookResponse = bookQueryService.getBookById(id);
            if (bookResponse.isIsread()) {
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }

    }

}
