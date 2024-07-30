package com.example.demo.services;

import com.example.demo.entities.SignUpInformation;
import com.example.demo.entities.StudentInformation;

import java.util.List;
import java.util.Optional;

public interface StudentInformationService {

    // Create Operation
    public StudentInformation createStudentInformation (StudentInformation studentInformation);

    // Read Operations
    public Optional<StudentInformation> getStudentInformation(String studentId);

    public List<StudentInformation> getAllStudentInformation();

    // Update Operation
    public Optional<StudentInformation> updateStudentInformation(String studentId, StudentInformation studentInformation);

    // Delete Operation
    public Optional<StudentInformation> deleteStudentInformation(String studentId);

    // Duplicate check function
    public Boolean isStudentEmailDuplicate(String studentEmail);

    public Boolean isStudentPhoneNumberDuplicate(String studentPhoneNumber);

    public Boolean isStudentIdNumberDuplicate(String studentIDNumber);

    public Boolean isStudentEmailDuplicate(String studentEmail, String studentId);

    public Boolean isStudentPhoneNumberDuplicate(String studentPhoneNumber, String studentId);

    public Boolean isStudentIdNumberDuplicate(String studentIDNumber, String studentId);
}
