package com.BrrowingServices.BrrowingService.Command.event;

import lombok.Data;

import java.util.Date;
@Data
public class BorrowCreatedEvent {
    private String id;
    private String BookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
}
