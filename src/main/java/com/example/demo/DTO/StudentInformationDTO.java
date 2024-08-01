package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;

// facilitates transfer of data between client and server
@Data
public class StudentInformationDTO {
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private String studentPhoneNumber;
    private Date studentDateOfBirth;
    private String studentPresentAddress;
    private String studentIDNumber;
    private String studentAcademicYear;
    private String studentNationality;
    private String studentScholarshipStatus;
    private boolean studentScholarshipsGained_CheveningScholarship;
    private boolean studentScholarshipsGained_DeansScholarship;
    private String studentScholarshipsGained_Other;
    private String studentDegreeProgram;
    private String studentCoreModule1;
    private String studentCoreModule2;
    private String studentElectiveModule1;
    private String studentElectiveModule2;
    private String studentElectiveModule3;
}
