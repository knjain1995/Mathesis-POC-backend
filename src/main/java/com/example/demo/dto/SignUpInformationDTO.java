package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpInformationDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Date dateofbirth;
    private String password;
    private boolean newsletterintent;
}
