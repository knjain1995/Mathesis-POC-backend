package com.example.demo.enums;

// centralize error messages for studentInformation Operations
public enum StudentInformationErrorMessage {
    //Enum to extract message based on the error text
    DUPLICATE_STUDENT_INFO("Duplicate Student Information Found! Check Email, Phone Number and StudentID"),
    STUDENT_INFO_NOT_FOUND("Student Information Not Found!"),
    ERROR_CREATING_STUDENT_INFO("Error Creating Student Information!"),
    ERROR_RETRIEVING_STUDENT_INFO("Error Retrieving Student Information!"),
    ERROR_UPDATING_STUDENT_INFO("Error Updating Student Information!"),
    ERROR_DELETING_STUDENT_INFO("Error Deleting Student Information!");

    // message variable to store corresponding message
    private final String errorMessage;

    // Setter
    StudentInformationErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // Getter
    public String getStudentInformationErrorMessage() {
        return errorMessage;
    }

}
