package com.example.demo.services;

import com.example.demo.entities.StudentInformation;
import com.example.demo.repositories.StudentInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentInformationServiceImpl implements StudentInformationService {

    @Autowired
    private StudentInformationRepository studentInformationRepository;

    @Override
    public StudentInformation createStudentInformation(StudentInformation studentInformation) {
        return studentInformationRepository.save(studentInformation);
    }

    @Override
    public Optional<StudentInformation> getStudentInformation(String studentId) {
        return studentInformationRepository.findById(studentId);
    }

    @Override
    public List<StudentInformation> getAllStudentInformation() {
        return studentInformationRepository.findAll();
    }

    @Override
    public Optional<StudentInformation> updateStudentInformation(String studentId, StudentInformation studentInformation) {
        Optional<StudentInformation> studentInformationToBeUpdated = studentInformationRepository.findById(studentId);
        if (studentInformationToBeUpdated.isPresent()) {
            studentInformation.setId(studentId);
            return Optional.of(studentInformationRepository.save(studentInformation));
        }
        else {
            return Optional.empty();
        }
    }


    @Override
    public Optional<StudentInformation> deleteStudentInformation(String studentId) {
        Optional<StudentInformation> studentInformationToBeDeleted = studentInformationRepository.findById(studentId);
        if (studentInformationToBeDeleted.isPresent()) {
            studentInformationRepository.delete(studentInformationToBeDeleted.get());
            return studentInformationToBeDeleted;
        }
        else {
            return Optional.empty();
        }
    }


    @Override
    public Boolean isStudentEmailDuplicate(String studentEmail) {
        return studentInformationRepository.findByStudentEmail(studentEmail).isPresent();
    }

    @Override
    public Boolean isStudentPhoneNumberDuplicate(String studentPhoneNumber) {
        return studentInformationRepository.findByStudentPhoneNumber(studentPhoneNumber).isPresent();
    }

    @Override
    public Boolean isStudentIdNumberDuplicate(String studentIDNumber) {
        return studentInformationRepository.findByStudentIDNumber(studentIDNumber).isPresent();
    }

    @Override
    public Boolean isStudentEmailDuplicate(String studentEmail, String studentId) {
        Optional<StudentInformation> existingStudentInformation = studentInformationRepository.findByStudentEmail(studentEmail);
        if (existingStudentInformation.isPresent() && !existingStudentInformation.get().getId().equals(studentId)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean isStudentPhoneNumberDuplicate(String studentPhoneNumber, String studentId) {
        Optional<StudentInformation> existingStudentInformation = studentInformationRepository.findByStudentPhoneNumber(studentPhoneNumber);
        if (existingStudentInformation.isPresent() && !existingStudentInformation.get().getId().equals(studentId)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean isStudentIdNumberDuplicate(String studentIDNumber, String studentId) {
        Optional<StudentInformation> existingStudentInformation = studentInformationRepository.findByStudentIDNumber(studentIDNumber);
        if (existingStudentInformation.isPresent() && !existingStudentInformation.get().getId().equals(studentId)) {
            return true;
        }
        else {
            return false;
        }
    }
}
