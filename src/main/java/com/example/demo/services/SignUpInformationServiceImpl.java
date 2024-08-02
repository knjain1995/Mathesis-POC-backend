package com.example.demo.services;

import com.example.demo.repositories.SignUpInformationRepository;
import com.example.demo.entities.LoginInformation;
import com.example.demo.entities.SignUpInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

// indicates that this is a spring managed service component which should be picked up during component scanning (Service Layer)
@Service
@RequiredArgsConstructor
public class SignUpInformationServiceImpl implements SignUpInformationService {

    @Autowired  // Spring will automatically inject an instance of SignUpInformationRepository into this field.
    private SignUpInformationRepository signUpInformationRepository;
    private final MongoTemplate mongoTemplate;

    // now we got our CRUD operations

    // Create of CRUD operations

    // Add SignUpInformation
    @Override
    public ResponseEntity<?> createSignUpInformation(SignUpInformation signUpInformation) {
        if (isSignUpInformationDuplicate(signUpInformation)) {
            return new ResponseEntity<>("Email Or Phone Number Already Used In Another SignUp!", HttpStatus.CONFLICT);
        }
        SignUpInformation savedSignUpInformation = signUpInformationRepository.save(signUpInformation);
        return new ResponseEntity<>(savedSignUpInformation, HttpStatus.CREATED);
    }

    // Get all SignUpInformation
    @Override
    public ResponseEntity<?> getAllSignUpInformation() {
        List<SignUpInformation> allSignUpInformation = signUpInformationRepository.findAll();
        if (allSignUpInformation.isEmpty()) {
            return new ResponseEntity<>("There Are Currently No SignUps!", HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(allSignUpInformation, HttpStatus.OK);
        }
    }

    // Get SignUpInformation for particular ID
    @Override
    public ResponseEntity<?> getSignUpInformationById(String signUpID) {
        SignUpInformation signUpInformation = signUpInformationRepository.findById(signUpID).orElse(null);
        if (signUpInformation == null) {
            return new ResponseEntity<>("Sign Up Details Not Found", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(signUpInformation, HttpStatus.OK);
        }
    }

    // Update SignUpInformation for the particular ID
    @Override
    public ResponseEntity<?> updateSignUpInformation(String signUpID, SignUpInformation signUpInformation) {
        SignUpInformation existingSignUpInformation = signUpInformationRepository.findById(signUpID).orElse(null); // find document with ID
        if (isSignUpInformationDuplicate(signUpInformation, signUpID)) {
            return new ResponseEntity<>("Email Or Phone Number Already Used In Another SignUp!", HttpStatus.CONFLICT);
        }
        if (existingSignUpInformation==null) {
            return new ResponseEntity<>("Sign Up Information Not Found", HttpStatus.NOT_MODIFIED);
        }
    //            existingSignUpInformation.setId(signUpID); // set existing id in the updated object
        existingSignUpInformation.setFirstname(signUpInformation.getFirstname());
        existingSignUpInformation.setLastname(signUpInformation.getLastname());
        existingSignUpInformation.setEmail(signUpInformation.getEmail());
        existingSignUpInformation.setPhone(signUpInformation.getPhone());
        existingSignUpInformation.setDateofbirth(signUpInformation.getDateofbirth());
        existingSignUpInformation.setPassword(signUpInformation.getPassword());
        existingSignUpInformation.setNewsletterintent(signUpInformation.isNewsletterintent());

        return new ResponseEntity<>(signUpInformationRepository.save(existingSignUpInformation), HttpStatus.OK);
    }

    // Delete SignUpInformation for the particular ID
    @Override
    public ResponseEntity<?> deleteSignUpInformation(String signUpID) {
        SignUpInformation existingSignUpInformation = signUpInformationRepository.findById(signUpID).orElse(null); // find document with ID
        if (existingSignUpInformation == null) {
            return new ResponseEntity<>("Sign Up Information Not Found", HttpStatus.NOT_MODIFIED);
        }
        else {
            signUpInformationRepository.delete(existingSignUpInformation);    //delete the signUpData
            return new ResponseEntity<>(existingSignUpInformation, HttpStatus.OK);
        }
    }

    // Get SignUpInformation for the particular Email and Password combination
    @Override
    public ResponseEntity<?> findSignUpInformationByEmailAndPassword(LoginInformation loginInformation) {
        SignUpInformation existingLoginInformation = signUpInformationRepository.findByEmailAndPassword(loginInformation.getEmail(), loginInformation.getPassword()).orElse(null);
        if (existingLoginInformation == null) {
            return new ResponseEntity<>("Sign Up Information Not Found! Check Email And Password!", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(existingLoginInformation, HttpStatus.OK);
        }
    }

    //    collate check for email and phone number for create function
    @Override
    public Boolean isSignUpInformationDuplicate(SignUpInformation signUpInformation) {
        return (isEmailDuplicate(signUpInformation.getEmail())
                || isPhoneDuplicate(signUpInformation.getPhone()));
    }

    //    collate check for email and phone number for update function
    @Override
    public Boolean isSignUpInformationDuplicate(SignUpInformation signUpInformation, String signUpID) {
        return (isEmailDuplicate(signUpInformation.getEmail(), signUpID)
                || isPhoneDuplicate(signUpInformation.getPhone(), signUpID));
    }

    // function to return if value in the Email field already exists for some document in our DB
    @Override
    public Boolean isEmailDuplicate(String signUpEmail) {
        return signUpInformationRepository.findByEmail(signUpEmail).isPresent();
    }

    // function to return if value in the Phone field already exists for some document in our DB
    @Override
    public Boolean isPhoneDuplicate(String signUpPhone) {
        return signUpInformationRepository.findByPhone(signUpPhone).isPresent();
    }

    // function to return if value in the Email field already exists for some document in our DB not considering the document of the ID being updated
    @Override
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
    @Override
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
