package com.example.demo.Exceptions;

public class DuplicateStudentInformationException extends RuntimeException {
    public DuplicateStudentInformationException(String message) {
        super(message);
    }
}
