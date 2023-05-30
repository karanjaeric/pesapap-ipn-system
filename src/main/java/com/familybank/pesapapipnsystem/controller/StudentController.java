/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.controller;

import com.familybank.pesapapipnsystem.dto.ResponseObject;
import com.familybank.pesapapipnsystem.exceptions.StudentAlreadyExistException;
import com.familybank.pesapapipnsystem.exceptions.StudentNotFoundException;
import com.familybank.pesapapipnsystem.model.Student;
import com.familybank.pesapapipnsystem.service.StudentService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author erick.karanja_cellul
 */
@RestController
@RequestMapping("v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity createStudent(@RequestBody Student student) {
        try {
            return studentService.createStudent(student);
        } catch (StudentAlreadyExistException ex) {
            return ResponseEntity.status(400).body(new ResponseObject(null, ex.getMessage()));
        }
    }

    @GetMapping("/retrieve")
    public ResponseEntity retrieveStudent(@RequestParam(name = "registrationNumber", required = true) String registrationNumber) {
        try {
            return studentService.retrieveStudent(registrationNumber);
        } catch (StudentNotFoundException ex) {
            return ResponseEntity.status(404).body(new ResponseObject(null, ex.getMessage()));
        }

    }

}
