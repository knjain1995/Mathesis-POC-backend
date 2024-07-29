package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

// REPOSITORY INTERFACE
// Our document and ID's type are the parameters in the form of a Generic
public interface SignUpInformationRepository extends MongoRepository<SignUpInformation, String> {
//    by extending MongoRepository, we now have methods for performing common database operations save, delete, find etc
}