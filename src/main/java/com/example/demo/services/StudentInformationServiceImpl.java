package com.example.demo.services;

import com.example.demo.Enums.StudentInformationErrorMessage;
import com.example.demo.Exceptions.DuplicateStudentInformationException;
import com.example.demo.Exceptions.StudentInformationNotFoundException;
import com.example.demo.entities.StudentInformation;
import com.example.demo.repositories.StudentInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentInformationServiceImpl implements StudentInformationService {

    @Autowired
    private StudentInformationRepository studentInformationRepository;

    @Override
    public StudentInformation createStudentInformation(StudentInformation studentInformation) {
        if (isStudentInformationDuplicate(studentInformation)) {
            throw new DuplicateStudentInformationException(StudentInformationErrorMessage.DUPLICATE_STUDENT_INFO.getStudentInformationErrorMessage());
//            throw new RuntimeException("Duplicate Student Information Found");
        }
        try {
            return studentInformationRepository.save(studentInformation);
        }
        catch (RuntimeException ex) {
            throw new RuntimeException(StudentInformationErrorMessage.ERROR_CREATING_STUDENT_INFO.getStudentInformationErrorMessage(), ex);
        }
    }

    @Override
    public Optional<StudentInformation> getStudentInformation(String studentId) {
        Optional<StudentInformation> studentInformation = studentInformationRepository.findById(studentId);
        if (!studentInformation.isPresent()) {
            throw new StudentInformationNotFoundException(StudentInformationErrorMessage.STUDENT_INFO_NOT_FOUND.getStudentInformationErrorMessage());
        }
        return studentInformation;
    }

    @Override
    public List<StudentInformation> getAllStudentInformation() {
        List<StudentInformation> studentInformationList = studentInformationRepository.findAll();
        if (studentInformationList.isEmpty()) {
            throw new StudentInformationNotFoundException(StudentInformationErrorMessage.STUDENT_INFO_NOT_FOUND.getStudentInformationErrorMessage());
        }
        return studentInformationList;
    }

    @Override
    public Optional<StudentInformation> updateStudentInformation(String studentId, StudentInformation studentInformation) {

        Optional<StudentInformation> studentInformationToBeUpdated = studentInformationRepository.findById(studentId);
        System.out.println("StudentInfoDuplicate: "+isStudentInformationDuplicate(studentInformationToBeUpdated.get(), studentId));

        if (!studentInformationToBeUpdated.isPresent()) {
            throw new StudentInformationNotFoundException(StudentInformationErrorMessage.STUDENT_INFO_NOT_FOUND.getStudentInformationErrorMessage());
        }

        if(isStudentInformationDuplicate(studentInformation, studentId)) {
            throw new DuplicateStudentInformationException(StudentInformationErrorMessage.DUPLICATE_STUDENT_INFO.getStudentInformationErrorMessage());
//               throw new RuntimeException("Duplicate Student Information Found");
        }

        studentInformation.setId(studentId);
        try {
            return Optional.of(studentInformationRepository.save(studentInformation));
        }
        catch (RuntimeException ex) {
            throw new RuntimeException(StudentInformationErrorMessage.ERROR_UPDATING_STUDENT_INFO.getStudentInformationErrorMessage(), ex);
        }

    }


    @Override
    public Optional<StudentInformation> deleteStudentInformation(String studentId) {
        Optional<StudentInformation> studentInformationToBeDeleted = studentInformationRepository.findById(studentId);

        if (!studentInformationToBeDeleted.isPresent()) {
            throw new StudentInformationNotFoundException(StudentInformationErrorMessage.STUDENT_INFO_NOT_FOUND.getStudentInformationErrorMessage());
        }

        try {
            studentInformationRepository.delete(studentInformationToBeDeleted.get());
            return studentInformationToBeDeleted;
        }
        catch (RuntimeException ex) {
            throw new RuntimeException(StudentInformationErrorMessage.ERROR_DELETING_STUDENT_INFO.getStudentInformationErrorMessage(), ex);
        }
    }


//    private StudentInformation mapToEntity(StudentInformationDTO studentInformationDTO) {
//        StudentInformation studentInformation = new StudentInformation();
//        studentInformation.setStudentEmail(studentInformationDTO.getStudentEmail());
//
//    }



    // in case of creating new studentInformation check if value of any of the three fields (studentEmail, studentPhoneNumber, studentIDNumber)
    // matches the field value in the current form
    @Override
    public Boolean isStudentInformationDuplicate(StudentInformation studentInformation) {
        System.out.println("StudentEmailDup: "+isStudentEmailDuplicate(studentInformation.getStudentEmail()));
        return (isStudentEmailDuplicate(studentInformation.getStudentEmail()) ||
                isStudentPhoneNumberDuplicate(studentInformation.getStudentPhoneNumber()) ||
                isStudentIdNumberDuplicate(studentInformation.getStudentIDNumber()));
    }

    // check if value of any of the three fields (studentEmail, studentPhoneNumber, studentIDNumber) matches the field value in the current form
    // but in case of editing the form ignore the forms own value present in the database
    @Override
    public Boolean isStudentInformationDuplicate(StudentInformation studentInformation, String studentID) {
        System.out.println("StudentEmailDup: "+isStudentEmailDuplicate(studentInformation.getStudentEmail(), studentID));
        System.out.println("PhoneDup: "+isStudentPhoneNumberDuplicate(studentInformation.getStudentPhoneNumber(), studentID));
        System.out.println("StudentIDDup: "+isStudentIdNumberDuplicate(studentInformation.getStudentIDNumber(), studentID));
        return (isStudentEmailDuplicate(studentInformation.getStudentEmail(), studentID) ||
                isStudentPhoneNumberDuplicate(studentInformation.getStudentPhoneNumber(), studentID) ||
                isStudentIdNumberDuplicate(studentInformation.getStudentIDNumber(), studentID));
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
        System.out.println("1 Student Email: "+studentEmail);
        System.out.println("1 existingStudentEmail: "+existingStudentInformation.get().getStudentEmail());
        System.out.println("1 Existing Student Info: "+existingStudentInformation.isPresent());
        System.out.println("1 !Existing Student equals id: "+existingStudentInformation.get().getId().equals(studentId));
        System.out.println("1 Existing ID: "+existingStudentInformation.get().getId());
        System.out.println("1 Student ID: "+studentId);

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
        System.out.println("2 Existing Student Info: "+existingStudentInformation.isPresent());
        System.out.println("2 !Existing Student equals id: "+existingStudentInformation.get().getId().equals(studentId));
        System.out.println("2 Existing ID: "+existingStudentInformation.get().getId());
        System.out.println("2 Student ID: "+studentId);
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
        System.out.println("3 Existing Student Info: "+existingStudentInformation.isPresent());
        System.out.println("3 !Existing Student equals id: "+existingStudentInformation.get().getId().equals(studentId));
        System.out.println("3 Existing ID: "+existingStudentInformation.get().getId());
        System.out.println("3 Student ID: "+studentId);

        if (existingStudentInformation.isPresent() && !existingStudentInformation.get().getId().equals(studentId)) {
            return true;
        }
        else {
            return false;
        }
    }
}
