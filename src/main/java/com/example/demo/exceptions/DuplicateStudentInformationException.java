package com.example.demo.exceptions;

public class DuplicateStudentInformationException extends RuntimeException {
    public DuplicateStudentInformationException(String message) {
        super(message);
    }
}
