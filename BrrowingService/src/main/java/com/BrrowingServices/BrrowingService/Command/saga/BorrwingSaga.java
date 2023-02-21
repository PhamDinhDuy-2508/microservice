package com.BrrowingServices.BrrowingService.Command.saga;

import com.BrrowingServices.BrrowingService.Command.command.BorroqwDeletedCommand;
import com.BrrowingServices.BrrowingService.Command.command.SendMessageCommand;
import com.BrrowingServices.BrrowingService.Command.command.UpdateBookStatusCommand;
import com.BrrowingServices.BrrowingService.Command.event.BookUpdateCommonEvent;
import com.BrrowingServices.BrrowingService.Command.event.BorrowCreatedEvent;
import com.BrrowingServices.BrrowingService.Command.model.BookCommonReponseModel;
import com.BrrowingServices.BrrowingService.Command.model.EmployeeResponseModel;
import com.BrrowingServices.BrrowingService.Query.queries.GetDetailBook;
import com.BrrowingServices.BrrowingService.Query.queries.GetDetailEmployee;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class BorrwingSaga {
    @Autowired
    private  transient CommandGateway commandGateway  ;

    @Autowired
    private  transient QueryGateway queryGateway ;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(BorrowCreatedEvent  event) throws Exception {
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
            } else {
                throw new Exception("Sach da duoc muon");
            }
        }
        catch (Exception e) {
            RollBackRecord(event.getId());
        }
    }
    @SagaEventHandler(associationProperty = "bookId")
    public void handle(BookUpdateCommonEvent event){
        try {
            GetDetailEmployee getDetailEmployee =  new GetDetailEmployee() ;
            getDetailEmployee.setEmployeeId(event.getEmployeeId());
            EmployeeResponseModel employeeResponseModel =  queryGateway.query(
                    getDetailEmployee  , ResponseTypes.instanceOf(EmployeeResponseModel.class)
            ).join()  ;
            if(employeeResponseModel.getIsDiscipline() == true) {
                throw new Exception("nhan vien bi ki luat") ;
            }
            else {
                commandGateway.sendAndWait(new SendMessageCommand("phamdinhduy" ,  "181193")) ;
            }
        }
        catch (Exception e) {

        }
    }
    public void RollBackRecord(String id_record) {
        BorroqwDeletedCommand borrowDeletedCommand =  new BorroqwDeletedCommand() ;
        borrowDeletedCommand.setBookId(id_record);
        commandGateway.sendAndWait(borrowDeletedCommand) ;
    }
    public void RollBackStatus() {

    }
}
