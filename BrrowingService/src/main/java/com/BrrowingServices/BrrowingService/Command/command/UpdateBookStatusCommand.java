package com.BrrowingServices.BrrowingService.Command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
public class UpdateBookStatusCommand {
    @TargetAggregateIdentifier
    private String id ;
    private  String bookId ;
    private  Boolean isReady ;
    private String employeeId ;
    private  String borrowId ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }
}
