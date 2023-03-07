package com.BookService.BookService.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookUpdateStatusDTO {
    private String BookId ;
    private  Boolean IsRead ;
}
