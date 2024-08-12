package com.example.demo.entities;

import com.example.demo.enums.Role;
import lombok.Data;

@Data
public class LoginInformation { //entity for login information {used for authenticating login}
    String email;
    String password;
}
