package com.example.demo.services;

import com.example.demo.entities.LoginInformation;
import com.example.demo.entities.SignUpInformation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

// indicates that this is a spring managed service component which should be picked up during component scanning (Service Layer)
public interface SignUpInformationService {

    // Add SignUpInformation
    ResponseEntity<?> createSignUpInformation(SignUpInformation signUpInformation);

    // Get all SignUpInformation
//    List<SignUpInformation> getAllSignUpInformation();
    ResponseEntity<?> getAllSignUpInformation();

    // Get SignUpInformation for particular ID
    ResponseEntity<?> getSignUpInformationById(String signUpID);

    // Update SignUpInformation for the particular ID
    ResponseEntity<?> updateSignUpInformation(String signUpID, SignUpInformation signUpInformation);

    // Delete SignUpInformation for the particular ID
    ResponseEntity<?> deleteSignUpInformation(String signUpID);

    // Get SignUpInformation for the particular Email and Password combination
    ResponseEntity<?> findSignUpInformationByEmailAndPassword(LoginInformation loginInformation);

    //////////////////////////////
    // Duplicate check function //
    //////////////////////////////

    // function to return if value in the Email field already exists for some document in our DB
    Boolean isEmailDuplicate(String signUpEmail);

    // function to return if value in the Phone field already exists for some document in our DB
    Boolean isPhoneDuplicate(String signUpPhone);

    // function to return if value in the Email field already exists for some document in our DB not considering the document of the ID being updated
    Boolean isEmailDuplicate(String signUpEmail, String signUpID);

    // function to return if value in the Phone field already exists for some document in our DB not considering the document of the ID being updated
    Boolean isPhoneDuplicate(String signUpPhone, String signUpID);

    // function to check if Student information is duplicate
    Boolean isSignUpInformationDuplicate(SignUpInformation signUpInformation);

    // function to check if Student information is duplicate
    Boolean isSignUpInformationDuplicate(SignUpInformation signUpInformation, String signUpID);


}
