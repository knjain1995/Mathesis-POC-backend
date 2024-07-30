package com.example.demo.controller;

import com.example.demo.entities.SignUpInformation;
import com.example.demo.entities.StudentInformation;
import com.example.demo.services.StudentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/studentInformationData")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentInformationController {

    @Autowired
    private StudentInformationService studentInformationService;

    //Create Operation
    @PostMapping
    public ResponseEntity<StudentInformation> createStudentInformation(@RequestBody StudentInformation studentInformation) {
        try {
            if(studentInformationService.isStudentEmailDuplicate(studentInformation.getStudentEmail()) |
                studentInformationService.isStudentPhoneNumberDuplicate(studentInformation.getStudentPhoneNumber()) |
                studentInformationService.isStudentIdNumberDuplicate(studentInformation.getStudentIDNumber())) {

                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            StudentInformation createdStudentInformation = studentInformationService.createStudentInformation(studentInformation);
            return new ResponseEntity<>(createdStudentInformation, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
         return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<StudentInformation>> getAllStudentInformation() {
        List<StudentInformation> allStudentInformation = studentInformationService.getAllStudentInformation();
        if (allStudentInformation.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(allStudentInformation, HttpStatus.OK);
        }
//        return new ResponseEntity<>(allStudentInformation, HttpStatus.OK);
    }


    @GetMapping("/{studentId}")
    public ResponseEntity<StudentInformation> getStudentInformation(@PathVariable String studentId) {
        Optional<StudentInformation> studentInformationForId = studentInformationService.getStudentInformation(studentId);
        return studentInformationForId.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentInformation> updateStudentInformation(@PathVariable String studentId, @RequestBody StudentInformation studentInformation) {
        try {
            if(studentInformationService.isStudentEmailDuplicate(studentInformation.getStudentEmail(), studentId) |
            studentInformationService.isStudentPhoneNumberDuplicate(studentInformation.getStudentPhoneNumber(), studentId) |
            studentInformationService.isStudentIdNumberDuplicate(studentInformation.getStudentIDNumber(), studentId)) {

                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            Optional<StudentInformation> updatedStudentInformation = studentInformationService.updateStudentInformation(studentId, studentInformation);
            return updatedStudentInformation.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
//        Optional<StudentInformation> updatedStudentInformation = studentInformationService.updateStudentInformation(studentId, studentInformation);
//        return updatedStudentInformation.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{studentId}")
    public ResponseEntity<StudentInformation> deleteStudentInformation(@PathVariable String studentId) {
        Optional<StudentInformation> deletedStudentInformation = studentInformationService.deleteStudentInformation(studentId);
        return deletedStudentInformation.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
