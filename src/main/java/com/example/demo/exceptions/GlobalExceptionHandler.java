package com.example.demo.exceptions;

import com.example.demo.enums.StudentInformationErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle exceptions of type DuplicateStudentInformationException thrown by Controller
    @ExceptionHandler(DuplicateStudentInformationException.class)
    public ResponseEntity<String> handleDuplicateStudentInformationException(DuplicateStudentInformationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Handle exceptions of type StudentInformationNotFoundException thrown by Controller
    @ExceptionHandler(StudentInformationNotFoundException.class)
    public ResponseEntity<String> handleStudentInformationNotFoundException(StudentInformationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle exceptions of other types
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Show Error message from Enum
        return new ResponseEntity<>(StudentInformationErrorMessage.ERROR_CREATING_STUDENT_INFO.getStudentInformationErrorMessage() +": "+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
