package com.example.demo.Exceptions;

public class StudentInformationNotFoundException extends RuntimeException {
    public StudentInformationNotFoundException(String message) {
        super(message);
    }
}
