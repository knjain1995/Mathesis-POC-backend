package com.example.demo.mappers;

import com.example.demo.dto.StudentInformationDTO;
import com.example.demo.entities.StudentInformation;

public class StudentInformationMapper {

    // This method takes a StudentInformationDTO object and converts it to a StudentInformation entity. Each field in the DTO is copied to the corresponding field in the entity.
    public StudentInformation mapToEntity(StudentInformationDTO studentInformationDTO) {

        if (studentInformationDTO == null) {
            return null;
        }

        StudentInformation studentInformationEntity = new StudentInformation();
        studentInformationEntity.setStudentFirstName(studentInformationDTO.getStudentFirstName());
        studentInformationEntity.setStudentLastName(studentInformationDTO.getStudentLastName());
        studentInformationEntity.setStudentEmail(studentInformationDTO.getStudentEmail());
        studentInformationEntity.setStudentPhoneNumber(studentInformationDTO.getStudentPhoneNumber());
        studentInformationEntity.setStudentDateOfBirth(studentInformationDTO.getStudentDateOfBirth());
        studentInformationEntity.setStudentPresentAddress(studentInformationDTO.getStudentPresentAddress());
        studentInformationEntity.setStudentIDNumber(studentInformationDTO.getStudentIDNumber());
        studentInformationEntity.setStudentAcademicYear(studentInformationDTO.getStudentAcademicYear());
        studentInformationEntity.setStudentNationality(studentInformationDTO.getStudentNationality());
        studentInformationEntity.setStudentScholarshipStatus(studentInformationDTO.getStudentScholarshipStatus());
        studentInformationEntity.setStudentScholarshipsGained_CheveningScholarship(studentInformationDTO.isStudentScholarshipsGained_CheveningScholarship());
        studentInformationEntity.setStudentScholarshipsGained_DeansScholarship(studentInformationDTO.isStudentScholarshipsGained_DeansScholarship());
        studentInformationEntity.setStudentScholarshipsGained_Other(studentInformationDTO.getStudentScholarshipsGained_Other());
        studentInformationEntity.setStudentDegreeProgram(studentInformationDTO.getStudentDegreeProgram());
        studentInformationEntity.setStudentCoreModule1(studentInformationDTO.getStudentCoreModule1());
        studentInformationEntity.setStudentCoreModule2(studentInformationDTO.getStudentCoreModule2());
        studentInformationEntity.setStudentElectiveModule1(studentInformationDTO.getStudentElectiveModule1());
        studentInformationEntity.setStudentElectiveModule2(studentInformationDTO.getStudentElectiveModule2());
        studentInformationEntity.setStudentElectiveModule3(studentInformationDTO.getStudentElectiveModule3());

        return studentInformationEntity;

    }

    // This method converts a StudentInformation entity to a StudentInformationDTO. This is useful for sending data to the client.
    public StudentInformationDTO mapToDTO(StudentInformation studentInformationEntity) {
        if (studentInformationEntity == null) {
            return null;
        }

        StudentInformationDTO studentInformationDTO = new StudentInformationDTO();

        studentInformationDTO.setStudentFirstName(studentInformationEntity.getStudentFirstName());
        studentInformationDTO.setStudentLastName(studentInformationEntity.getStudentLastName());
        studentInformationDTO.setStudentEmail(studentInformationEntity.getStudentEmail());
        studentInformationDTO.setStudentPhoneNumber(studentInformationEntity.getStudentPhoneNumber());
        studentInformationDTO.setStudentDateOfBirth(studentInformationEntity.getStudentDateOfBirth());
        studentInformationDTO.setStudentPresentAddress(studentInformationEntity.getStudentPresentAddress());
        studentInformationDTO.setStudentIDNumber(studentInformationEntity.getStudentIDNumber());
        studentInformationDTO.setStudentAcademicYear(studentInformationEntity.getStudentAcademicYear());
        studentInformationDTO.setStudentNationality(studentInformationEntity.getStudentNationality());
        studentInformationDTO.setStudentScholarshipStatus(studentInformationEntity.getStudentScholarshipStatus());
        studentInformationDTO.setStudentScholarshipsGained_CheveningScholarship(studentInformationEntity.isStudentScholarshipsGained_CheveningScholarship());
        studentInformationDTO.setStudentScholarshipsGained_DeansScholarship(studentInformationEntity.isStudentScholarshipsGained_DeansScholarship());
        studentInformationDTO.setStudentScholarshipsGained_Other(studentInformationEntity.getStudentScholarshipsGained_Other());
        studentInformationDTO.setStudentDegreeProgram(studentInformationEntity.getStudentDegreeProgram());
        studentInformationDTO.setStudentCoreModule1(studentInformationEntity.getStudentCoreModule1());
        studentInformationDTO.setStudentCoreModule2(studentInformationEntity.getStudentCoreModule2());
        studentInformationDTO.setStudentElectiveModule1(studentInformationEntity.getStudentElectiveModule1());
        studentInformationDTO.setStudentElectiveModule2(studentInformationEntity.getStudentElectiveModule2());
        studentInformationDTO.setStudentElectiveModule3(studentInformationEntity.getStudentElectiveModule3());

        return studentInformationDTO;
    }
}
