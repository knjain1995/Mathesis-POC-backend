package com.example.demo.repositories;

import com.example.demo.entities.StudentInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentInformationRepository extends MongoRepository<StudentInformation, String> {
    Optional<StudentInformation> findByStudentEmail(String studentEmail);
    Optional<StudentInformation> findByStudentPhoneNumber(String studentPhoneNumber);
    Optional<StudentInformation> findByStudentIDNumber(String studentIDNumber);
}
