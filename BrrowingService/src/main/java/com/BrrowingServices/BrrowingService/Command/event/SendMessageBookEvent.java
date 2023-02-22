package com.BrrowingServices.BrrowingService.Command.event;

import lombok.Data;

@Data
public class SendMessageBookEvent {
    private  String bookId ;
    private  Boolean isReady ;
    private String employeeId ;
    private  String borrowId ;

    @Override
    public String toString() {
        return "SendMessageBookEvent{" +
                "bookId='" + bookId + '\'' +
                ", isReady=" + isReady +
                '}';
    }
}
