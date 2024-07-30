package com.example.demo.services;

import com.example.demo.entities.LoginInformation;
import com.example.demo.entities.SignUpInformation;

import java.util.List;
import java.util.Optional;

// indicates that this is a spring managed service component which should be picked up during component scanning (Service Layer)
public interface SignUpInformationService {

    // Add SignUpInformation
    public SignUpInformation createSignUpInformation(SignUpInformation signUpInformation);

    // Get all SignUpInformation
    public List<SignUpInformation> getAllSignUpInformation();

    // Get SignUpInformation for particular ID
    public Optional<SignUpInformation> getSignUpInformationById(String signUpID);

    // Update SignUpInformation for the particular ID
    public SignUpInformation updateSignUpInformation(String signUpID, SignUpInformation signUpInformation);

    // Delete SignUpInformation for the particular ID
    public Optional<SignUpInformation> deleteSignUpInformation(String signUpID);

    // Get SignUpInformation for the particular Email and Password combination
    public Optional<SignUpInformation> findSignUpInformationByEmailAndPassword(LoginInformation loginInformation);

    // function to return if value in the Email field already exists for some document in our DB
    public Boolean isEmailDuplicate(String signUpEmail);

    // function to return if value in the Phone field already exists for some document in our DB
    public Boolean isPhoneDuplicate(String signUpPhone);

    // function to return if value in the Email field already exists for some document in our DB not considering the document of the ID being updated
    public Boolean isEmailDuplicate(String signUpEmail, String signUpID);

    // function to return if value in the Phone field already exists for some document in our DB not considering the document of the ID being updated
    public Boolean isPhoneDuplicate(String signUpPhone, String signUpID);

}
