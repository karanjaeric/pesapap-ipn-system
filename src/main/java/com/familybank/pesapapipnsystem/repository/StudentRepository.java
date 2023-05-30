/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.repository;

import com.familybank.pesapapipnsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ekaranja
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByRegistrationNumber(String registrationNumber);

}
