package com.example.demo.controller;

import com.example.demo.DTO.StudentInformationDTO;
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

//    Create Operation
    @PostMapping
    public ResponseEntity<?> createStudentInformation(@RequestBody StudentInformationDTO studentInformationDTO) {
//        StudentInformation createdStudentInformation = studentInformationService.createStudentInformation(studentInformation);
//        return new ResponseEntity<>(createdStudentInformation, HttpStatus.CREATED);
        return studentInformationService.createStudentInformation(studentInformationDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllStudentInformation() {
//        List<StudentInformation> allStudentInformation = studentInformationService.getAllStudentInformation();
        return studentInformationService.getAllStudentInformation();
    }


    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentInformation(@PathVariable String studentId) {
//        Optional<StudentInformation> studentInformationForId = studentInformationService.getStudentInformation(studentId);
//        return studentInformationForId.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        return studentInformationService.getStudentInformation(studentId);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudentInformation(@PathVariable String studentId, @RequestBody StudentInformationDTO studentInformationDTO) {
//        Optional<StudentInformation> updatedStudentInformation = studentInformationService.updateStudentInformation(studentId, studentInformation);
//        return updatedStudentInformation.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        return studentInformationService.updateStudentInformation(studentId, studentInformationDTO);
    }


    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudentInformation(@PathVariable String studentId) {
//        Optional<StudentInformation> deletedStudentInformation = studentInformationService.deleteStudentInformation(studentId);
//        return deletedStudentInformation.map(res -> new ResponseEntity<>(res, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        return studentInformationService.deleteStudentInformation(studentId);
    }

}
