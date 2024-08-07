package com.example.demo.services;

import com.example.demo.dto.StudentInformationDTO;
import com.example.demo.entities.StudentInformation;
import org.springframework.http.ResponseEntity;

public interface StudentInformationService {

    // Create Operation
    ResponseEntity<?> createStudentInformation (StudentInformationDTO studentInformationDTO);

    // Read Operations
    ResponseEntity<?> getStudentInformation(String studentId);

    ResponseEntity<?> getAllStudentInformation();

    // Update Operation
    ResponseEntity<?> updateStudentInformation(String studentId, StudentInformationDTO studentInformationDTO);

    // Delete Operation
    ResponseEntity<?> deleteStudentInformation(String studentId);


    // Duplicate check function
    Boolean isStudentInformationDuplicate(StudentInformation studentInformation);
//
    Boolean isStudentInformationDuplicate(StudentInformation studentInformation, String studentId);

    Boolean isStudentEmailDuplicate(String studentEmail);

    Boolean isStudentPhoneNumberDuplicate(String studentPhoneNumber);

    Boolean isStudentIdNumberDuplicate(String studentIDNumber);

    Boolean isStudentEmailDuplicate(String studentEmail, String studentId);

    Boolean isStudentPhoneNumberDuplicate(String studentPhoneNumber, String studentId);

    Boolean isStudentIdNumberDuplicate(String studentIDNumber, String studentId);
}
