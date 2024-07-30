package com.example.demo.controller;

import com.example.demo.entities.LoginInformation;
import com.example.demo.entities.SignUpInformation;
import com.example.demo.services.SignUpInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/checkLoginCredentials")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginAuthenticationController {

    // injects SignUpInformationService in the controller which responsible for handling business logic related to signup information
    @Autowired
    private SignUpInformationService signUpInformationService;

    // maps HTTP Post request with login credentials to api/signup/login to check if login data is present and return SignUp Information
    @PostMapping
    public ResponseEntity<SignUpInformation> checkLoginCredentials(@RequestBody LoginInformation loginInformation) {
        Optional<SignUpInformation> loggedInSignUpData = signUpInformationService.findSignUpInformationByEmailAndPassword(loginInformation);
        return loggedInSignUpData.map(res-> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
