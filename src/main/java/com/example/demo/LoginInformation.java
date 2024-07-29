package com.example.demo;

import lombok.Data;

@Data
public class LoginInformation { //entity for login information {used for authenticating login}
    String email;
    String password;
}
