package com.commonservice.CommonService.Command.model;

import lombok.Data;

@Data
public class EmployeeResponseModel {
    private String Id;
    private String username;
    private String firstname;
    private String lastname;
    private String kin;
    private Boolean isDiscipline;
}
