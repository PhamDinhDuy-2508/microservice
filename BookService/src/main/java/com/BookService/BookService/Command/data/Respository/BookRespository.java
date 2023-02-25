package com.BookService.BookService.Command.data.Respository;

import com.BookService.BookService.Command.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRespository extends JpaRepository<Book, Long> {
    @Modifying
    @Query("update Book u set u.Name = ?1, u.Author = ?2 where u.Id = ?3")
    void updateInfoById(String name, String Author, String Id);
    @Query("select u from Book  u where u.Id = :id")
    Book getBookById(String id) ;
    @Modifying
    @Query("update Book  u set u.Isread =?1 where u.Id = ?2")
    void updateStatus(boolean isread , String ID) ;

}
