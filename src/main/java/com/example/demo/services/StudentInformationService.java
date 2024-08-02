package com.example.demo.services;

import com.example.demo.entities.StudentInformation;

import java.util.List;
import java.util.Optional;

public interface StudentInformationService {

    // Create Operation
    StudentInformation createStudentInformation (StudentInformation studentInformation);

    // Read Operations
    Optional<StudentInformation> getStudentInformation(String studentId);

    List<StudentInformation> getAllStudentInformation();

    // Update Operation
    Optional<StudentInformation> updateStudentInformation(String studentId, StudentInformation studentInformation);

    // Delete Operation
    Optional<StudentInformation> deleteStudentInformation(String studentId);


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
