package com.BookService.BookService.repository;

import com.BookService.BookService.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRespository extends JpaRepository<Book, Long> {
    @Query("DELETE from Book  b where b.Id =: id")
    void BookDeleteById(@Param("id") String id);

    @Modifying
    @Query("update Book b set b.Isread =:isread where b.Id=:id ")
    void UpdateBook(@Param("id") String id, @Param("isread") Boolean isread);

    @Query("select  b  from  Book b  where b.Id =:id ")
    Book getBookById(@Param("id") String id);

}
