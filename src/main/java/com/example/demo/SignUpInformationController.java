package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

// @RestController: indicates that class is a controller which handles web requests it @Controller + @ResponseBody - handles http requests and response in response body
// @RequestMapping: maps http requests to specified path to this controller class. All requests to this API will be handled by methods in this class
// @CrossOrigin: allows cross origin requests from the specified origin. It enables web servers to support requests from different origins


@RestController
@RequestMapping("/api/signup")
@CrossOrigin(origins = "http://localhost:4200")
public class SignUpInformationController {

    // injects SignUpInformationService in the controller which responsible for handling business logic related to signup information
    @Autowired
    private SignUpInformationService signUpInformationService;

    // maps HTTP GET requests to api/signup to the method signUpInformation
    @GetMapping
    public ResponseEntity<List<SignUpInformation>> getAllSignUpInformation() {
        List<SignUpInformation> allSignUpInformation = signUpInformationService.getAllSignUpInformation();
//        System.out.println(allSignUpInformation);
        return new ResponseEntity<>(allSignUpInformation, HttpStatus.OK);
    }

    // maps HTTP GET requests for specific id to api/ signup to the method signUpInformation
    @GetMapping("/{signUpID}")
    public ResponseEntity<SignUpInformation> getSignUpInformationById(@PathVariable String signUpID) {
        Optional<SignUpInformation> signUpInformation = signUpInformationService.getSignUpInformationById(signUpID);
//        System.out.println(signUpInformation);
        return signUpInformation.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // maps HTTP POST requests to api/signup to the method signUpInformation
    @PostMapping
    public ResponseEntity<SignUpInformation> signUpInformation(@RequestBody SignUpInformation signUpInformation) {  //get signup information as response body
        try {
            if(signUpInformationService.isEmailDuplicate(signUpInformation.getEmail()) | signUpInformationService.isPhoneDuplicate(signUpInformation.getPhone())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            SignUpInformation createdSignUpInformation = signUpInformationService.createSignUpInformation(signUpInformation);
            return new ResponseEntity<>(createdSignUpInformation, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // maps HTTP PUT request for specified id to api/ signup to the method signUpInformation
    @PutMapping("/{signUpID}")
    public ResponseEntity<SignUpInformation> updateSignUpInformation(@PathVariable String signUpID, @RequestBody SignUpInformation signUpInformation) {
        try {
            if(signUpInformationService.isEmailDuplicate(signUpInformation.getEmail(), signUpID) | signUpInformationService.isPhoneDuplicate(signUpInformation.getPhone(), signUpID)) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            SignUpInformation updatedSignUpInformation = signUpInformationService.updateSignUpInformation(signUpID, signUpInformation);
            return new ResponseEntity<>(updatedSignUpInformation, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // maps HTTP DELETE request for specified id to api/ signup to the method signUpInformation
    @DeleteMapping("/{signUpID}")
    public ResponseEntity<SignUpInformation> deleteSignUpInformation(@PathVariable String signUpID) {
        try {
            Optional<SignUpInformation> deletedSignUpInformation = signUpInformationService.deleteSignUpInformation(signUpID);
            return deletedSignUpInformation.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // maps HTTP Post request with login credentials to api/signup/login to check if login data is present and return SignUp Information
    @PostMapping("/checkLoginCredentials")
    public ResponseEntity<SignUpInformation> checkLoginCredentials(@RequestBody LoginInformation loginInformation) {
        Optional<SignUpInformation> loggedInSignUpData = signUpInformationService.findSignUpInformationByEmailAndPassword(loginInformation);
        return loggedInSignUpData.map(res-> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
