package com.example.demo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class StudentInformation {

    @Id
    private String id;
    private String studentFirstName;
    private String studentLastName;
    @Indexed(unique = true)
    private String studentEmail;
    @Indexed(unique = true)
    private String studentPhoneNumber;
    private Date studentDateOfBirth;
    private String studentPresentAddress;
    @Indexed(unique = true)
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
