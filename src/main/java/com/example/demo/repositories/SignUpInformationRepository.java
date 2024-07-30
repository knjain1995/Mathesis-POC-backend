package com.example.demo.repositories;

import com.example.demo.entities.SignUpInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// REPOSITORY INTERFACE
// Our document and ID's type are the parameters in the form of a Generic
public interface SignUpInformationRepository extends MongoRepository<SignUpInformation, String> {
//    by extending MongoRepository, we now have methods for performing common database operations save, delete, find etc
    Optional<SignUpInformation> findByEmailAndPassword (String email, String password); // Get document based on Email and Password. Used in login authentication
    Optional<SignUpInformation> findByEmail(String email);  // Used to find document using the Email as parameter. Used to check duplication during Creating and Updating Sign Up Information
    Optional<SignUpInformation> findByPhone(String phone);  // Used to find document using the Phone Number as parameter. Used to check duplication during Creating and Updating Sign Up Information
}