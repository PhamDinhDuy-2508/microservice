package com.BrrowingServices.BrrowingService.Query.projection;

import com.BrrowingServices.BrrowingService.Command.data.Borrow;
import com.BrrowingServices.BrrowingService.Command.data.Respository.BorrowRepository;
import com.BrrowingServices.BrrowingService.Command.model.BookCommonReponseModel;
import com.BrrowingServices.BrrowingService.Query.model.BorrowReponse;
import com.BrrowingServices.BrrowingService.Query.queries.GetByBookId;
import com.BrrowingServices.BrrowingService.Query.queries.GetByEmployeeId;
import com.BrrowingServices.BrrowingService.Query.queries.GetById;
import com.BrrowingServices.BrrowingService.Query.queries.GetDetailBook;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BorrowProjection {
    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    RestTemplate restTemplate;

    @QueryHandler
    public BorrowReponse handle(GetById getById) {
        BorrowReponse borrowReponse = new BorrowReponse();
        Borrow borrow = borrowRepository.findById(getById.getId());
        BeanUtils.copyProperties(borrow, borrowReponse);
        return borrowReponse;
    }

    @QueryHandler
    public List<BorrowReponse> handle(GetByBookId get) {
        List<Borrow> borrowList = borrowRepository.findByBookId(get.getBookId());
        List<BorrowReponse> borrowReponseList = new ArrayList<>();
        borrowList.parallelStream().forEach(borrow -> {
                    BorrowReponse borrowReponse = new BorrowReponse();
                    BeanUtils.copyProperties(borrow, borrowReponse);
                    borrowReponseList.add(borrowReponse);
                }
        );
        return borrowReponseList;
    }

    @QueryHandler
    public List<BorrowReponse> handle(GetByEmployeeId get) {
        List<Borrow> borrowList = borrowRepository.findByEmployeeId(get.getEmployeeId());
        List<BorrowReponse> borrowReponseList = new ArrayList<>();
        borrowList.parallelStream().forEach(borrow -> {
                    BorrowReponse borrowReponse = new BorrowReponse();
                    BeanUtils.copyProperties(borrow, borrowReponse);
                    borrowReponseList.add(borrowReponse);
                }
        );
        return borrowReponseList;
    }

    @QueryHandler
    public BookCommonReponseModel handle(GetDetailBook getDetailBook) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String json_str = restTemplate.exchange("localhost:9010/api/v1/book/getBook/ " + getDetailBook.getIdBook(), HttpMethod.GET,
                entity, String.class).getBody();
        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject = gson.fromJson(json_str, JsonObject.class);

        return convert(jsonObject);
    }

    public BookCommonReponseModel convert(JsonObject jsonObject) {
        BookCommonReponseModel bookCommonReponseModel = new BookCommonReponseModel();
        bookCommonReponseModel.setBookId(String.valueOf(jsonObject.get("Id")));
        bookCommonReponseModel.setIsReady(Boolean.valueOf(String.valueOf(jsonObject.get("Isread"))));
        return bookCommonReponseModel;
    }

}
