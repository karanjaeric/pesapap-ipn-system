/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.service;

import com.familybank.pesapapipnsystem.dto.ResponseObject;
import com.familybank.pesapapipnsystem.dto.StudentDto;
import com.familybank.pesapapipnsystem.exceptions.StudentAlreadyExistException;
import com.familybank.pesapapipnsystem.exceptions.StudentNotFoundException;
import com.familybank.pesapapipnsystem.model.Student;
import com.familybank.pesapapipnsystem.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ekaranja
 */
@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity createStudent(Student student) throws StudentAlreadyExistException {
        log.info("Received Student Object {}", student.toString());
        if (studentRepository.findByRegistrationNumber(student.getRegistrationNumber()) != null) {
            log.error("Student Already Exist");
            throw new StudentAlreadyExistException("A Student With this Registration Number Already Exist");
        }
        student.setActive(true);
        student = studentRepository.save(student);
        log.info("Student created Successfully");
        return ResponseEntity.ok(new ResponseObject(student, "Student created Successfully"));

    }

    public ResponseEntity retrieveStudent(String registrationNumber) throws StudentNotFoundException {
        Student student = studentRepository.findByRegistrationNumber(registrationNumber);
        if (student == null) {
            log.error("Student Does Not Exist");
            throw new StudentNotFoundException("Student Does Not Exist");
        }
        String studentName = student.getFirstname() + " " + student.getMiddlename() + " " + student.getSurname();
        return ResponseEntity.status(200).body(new StudentDto(studentName, student.getRegistrationNumber()));

    }

}
