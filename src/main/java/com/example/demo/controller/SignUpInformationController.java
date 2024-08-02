package com.example.demo.controller;

import com.example.demo.entities.SignUpInformation;
import com.example.demo.services.SignUpInformationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // maps HTTP POST requests to api/signup to the method signUpInformation
    @PostMapping
    public ResponseEntity<?> signUpInformation(@RequestBody SignUpInformation signUpInformation) {  //get signup information as response body
        return signUpInformationService.createSignUpInformation(signUpInformation);
    }

    // maps HTTP GET requests to api/signup to the method signUpInformation
    @GetMapping
    public ResponseEntity<?> getAllSignUpInformation() {
        return signUpInformationService.getAllSignUpInformation();
    }

    // maps HTTP GET requests for specific id to api/ signup to the method signUpInformation
    @GetMapping("/{signUpID}")
    public ResponseEntity<?> getSignUpInformationById(@PathVariable String signUpID) {
        return signUpInformationService.getSignUpInformationById(signUpID);
    }

    // maps HTTP PUT request for specified id to api/ signup to the method signUpInformation
    @PutMapping("/{signUpID}")
    public ResponseEntity<?> updateSignUpInformation(@PathVariable String signUpID, @RequestBody SignUpInformation signUpInformation) {
        return signUpInformationService.updateSignUpInformation(signUpID, signUpInformation);
    }

    // maps HTTP DELETE request for specified id to api/ signup to the method signUpInformation
    @DeleteMapping("/{signUpID}")
    public ResponseEntity<?> deleteSignUpInformation(@PathVariable String signUpID) {
        return signUpInformationService.deleteSignUpInformation(signUpID);
    }

}


