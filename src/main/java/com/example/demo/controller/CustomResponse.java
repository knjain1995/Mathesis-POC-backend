package com.example.demo.controller;

import com.example.demo.entities.StudentInformation;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomResponse {
    private String message;
    private HttpStatus status;
    private StudentInformation studentInformation;

//    public CustomResponse(String message, HttpStatus status, StudentInformation studentInformation) {
//        this.message = message;
//        this.status = status;
//        this.studentInformation = studentInformation;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public HttpStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(HttpStatus status) {
//        this.status = status;
//    }
//
//    public StudentInformation getStudentInformation() {
//        return studentInformation;
//    }
//
//    public void setStudentInformation(StudentInformation studentInformation) {
//        this.studentInformation = studentInformation;
//    }
}
