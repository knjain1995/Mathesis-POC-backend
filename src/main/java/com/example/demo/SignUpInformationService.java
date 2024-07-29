package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
