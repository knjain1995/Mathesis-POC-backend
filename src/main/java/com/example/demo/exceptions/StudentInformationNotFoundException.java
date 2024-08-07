package com.example.demo.exceptions;

public class StudentInformationNotFoundException extends RuntimeException {
    public StudentInformationNotFoundException(String message) {
        super(message);
    }
}
