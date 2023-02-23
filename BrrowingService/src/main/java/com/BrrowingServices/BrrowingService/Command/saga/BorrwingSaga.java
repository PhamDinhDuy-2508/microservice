package com.BrrowingServices.BrrowingService.Command.saga;

import com.BrrowingServices.BrrowingService.Command.command.BorroqwDeletedCommand;
import com.BrrowingServices.BrrowingService.Command.command.SendMessageCommand;
import com.BrrowingServices.BrrowingService.Command.command.UpdateBookStatusCommand;
import com.BrrowingServices.BrrowingService.Command.event.BookUpdateCommonEvent;
import com.BrrowingServices.BrrowingService.Command.event.BorrowCreatedEvent;
import com.BrrowingServices.BrrowingService.Command.model.BookCommonReponseModel;
import com.BrrowingServices.BrrowingService.Command.model.EmployeeResponseModel;
import com.BrrowingServices.BrrowingService.Query.projection.BorrowProjection;
import com.BrrowingServices.BrrowingService.Query.queries.GetDetailBook;
import com.BrrowingServices.BrrowingService.Query.queries.GetDetailEmployee;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class BorrwingSaga {
    @Autowired
    private transient CommandGateway commandGateway;
    Logger logger= LoggerFactory.getLogger(BorrwingSaga.class) ;

    @Autowired
    private transient QueryGateway queryGateway;

    @Autowired
    BorrowProjection borrowProjection ;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(BorrowCreatedEvent event) throws Exception {
        try {

            SagaLifecycle.associateWith("bookId", event.getBookId());
            GetDetailBook getDetailBook = new GetDetailBook();
            getDetailBook.setIdBook(event.getBookId());

            BookCommonReponseModel bookCommonReponseModel = queryGateway.query(
                    getDetailBook, ResponseTypes.instanceOf(BookCommonReponseModel.class)
            ).join();


            if (bookCommonReponseModel.getIsReady() == true) {
                UpdateBookStatusCommand updateBookStatusCommand = new UpdateBookStatusCommand();
                updateBookStatusCommand.setBookId(event.getBookId());
                updateBookStatusCommand.setIsReady(false);
                updateBookStatusCommand.setBorrowId(event.getId());
                updateBookStatusCommand.setEmployeeId(event.getEmployeeId());
                commandGateway.sendAndWait(updateBookStatusCommand);

            } else {
                throw new Exception("Sach da duoc muon");
            }
        } catch (Exception e) {
            logger.info("Roll Back");
            System.out.println(e.getMessage());
            RollBackRecord(event.getId() , event.getBookId());
        }
    }

    @SagaEventHandler(associationProperty = "bookId")
    public void handle(BookUpdateCommonEvent event) {
        try {
            GetDetailEmployee getDetailEmployee = new GetDetailEmployee();
            getDetailEmployee.setEmployeeId(event.getEmployeeId());
            EmployeeResponseModel employeeResponseModel = queryGateway.query(
                    getDetailEmployee, ResponseTypes.instanceOf(EmployeeResponseModel.class)
            ).join();
            if (employeeResponseModel.getIsDiscipline() == true) {
                throw new Exception("nhan vien bi ki luat");
            } else {
                commandGateway.sendAndWait(new SendMessageCommand("phamdinhduy", "181193"));
            }
        } catch (Exception e) {
            RollBackStatus(event.getBookId(),  event.getBorrowId() , event.getEmployeeId());
        }
    }

//    @SagaEventHandler(associationProperty = "id")
//    public void HandleRollBackRecord(String id) {
//        RollBackRecord(id);
//    }
//    @SagaEventHandler(associationProperty = "bookId")
//    public void HandleRollBackStatus(String bookId ,  String BorrowId , String employeeId) {
//        SagaLifecycle.associateWith("bookId" , bookId);
//        RollBackStatus(bookId , bookId ,  employeeId);
//    }
    public void RollBackRecord(String id_record, String id_book) {
        BorroqwDeletedCommand borrowDeletedCommand = new BorroqwDeletedCommand();
        borrowDeletedCommand.setId(id_record);
        borrowDeletedCommand.setBookId(id_book);
        commandGateway.sendAndWait(borrowDeletedCommand);
    }
    public  void RollBackStatus(String bookId , String BorrowId , String EmployeeId) {
        UpdateBookStatusCommand updateBookStatusCommand = new UpdateBookStatusCommand();
        updateBookStatusCommand.setBookId(bookId);
        updateBookStatusCommand.setIsReady(false);
        commandGateway.sendAndWait(updateBookStatusCommand) ;
    }

//    @SagaEventHandler(associationProperty = "id")
//    @EndSaga
//    public void handle(BorroqwDeletedCommand event) {
//        System.out.println("BorrowDeletedEvent in Saga for Borrowing Id : {} " +
//                event.getId());
//        commandGateway.sendAndWait(event)  ;
//        SagaLifecycle.end();
//    }




}
