package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

// indicates that this is a spring managed service component which should be picked up during component scanning (Service Layer)
@Service
public class SignUpInformationService {

    @Autowired  // Spring will automatically inject an instance of SignUpInformationRepository into this field.
    private SignUpInformationRepository signUpInformationRepository;    // now we got our CRUD operations

    // Create of CRUD operations

    // Add SignUpInformation
    public SignUpInformation createSignUpInformation(SignUpInformation signUpInformation) {
//        List<SignUpInformation> duplicateEmailSignUps = signUpInformationRepository.findBySignUpEmail(signUpInformation.getEmail());
//        if (duplicateEmailSignUps.size() > 0) {
//            return null;
//        }
//        else {
//            return signUpInformationRepository.save(signUpInformation); // Use save method from repository to save object in DB
//        }
        return signUpInformationRepository.save(signUpInformation); // Use save method from repository to save object in DB
    }

    // Get all SignUpInformation
    public List<SignUpInformation> getAllSignUpInformation() {
        return signUpInformationRepository.findAll();
    }

    // Get SignUpInformation for particular ID
    public Optional<SignUpInformation> getSignUpInformationById(String signUpID) {
        return signUpInformationRepository.findById(signUpID);
    }

    // Update SignUpInformation for the particular ID
    public SignUpInformation updateSignUpInformation(String signUpID, SignUpInformation signUpInformation) {
        Optional<SignUpInformation> existingSignUpInformation = signUpInformationRepository.findById(signUpID); // find document with ID
        if (existingSignUpInformation.isPresent()) {
            signUpInformation.setId(signUpID); // set existing id in the updated object
            return signUpInformationRepository.save(signUpInformation); // save object as document (overwrites existing object)
        }
        else {
            return null;
        }
    }

    // Delete SignUpInformation for the particular ID
    public Optional<SignUpInformation> deleteSignUpInformation(String signUpID) {
        Optional<SignUpInformation> existingSignUpInformation = signUpInformationRepository.findById(signUpID); // find document with ID
        if (existingSignUpInformation.isPresent()) {
            signUpInformationRepository.delete(existingSignUpInformation.get());    //delete the signUpData
            return existingSignUpInformation;
        }
        else {
            return Optional.empty();
        }
    }

    // Get SignUpInformation for the particular Email and Password combination
    public Optional<SignUpInformation> findSignUpInformationByEmailAndPassword(LoginInformation loginInformation) {
        return signUpInformationRepository.findByEmailAndPassword(loginInformation.email, loginInformation.password);
    }

    // function to return if value in the Email field already exists for some document in our DB
    public Boolean isEmailDuplicate(String signUpEmail) {
        return signUpInformationRepository.findByEmail(signUpEmail).isPresent();
    }

    // function to return if value in the Phone field already exists for some document in our DB
    public Boolean isPhoneDuplicate(String signUpPhone) {
        return signUpInformationRepository.findByPhone(signUpPhone).isPresent();
    }

    // function to return if value in the Email field already exists for some document in our DB not considering the document of the ID being updated
    public Boolean isEmailDuplicate(String signUpEmail, String signUpID) {

        Optional<SignUpInformation> existingSignUpInformation = signUpInformationRepository.findByEmail(signUpEmail);

        if(existingSignUpInformation.isPresent() && !existingSignUpInformation.get().getId().equals(signUpID)) {
            return true;
        }
        else {
            return false;
        }
    }

    // function to return if value in the Phone field already exists for some document in our DB not considering the document of the ID being updated
    public Boolean isPhoneDuplicate(String signUpPhone, String signUpID) {
        Optional<SignUpInformation> existingSignUpInformation = signUpInformationRepository.findByPhone(signUpPhone);
        if(existingSignUpInformation.isPresent() && !existingSignUpInformation.get().getId().equals(signUpID)) {
            return true;
        }
        else {
            return false;
        }
    }

}
