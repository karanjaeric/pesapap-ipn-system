/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.controller;

import com.familybank.pesapapipnsystem.dto.ResponseObject;
import com.familybank.pesapapipnsystem.exceptions.PaymentMethodAlreadyExist;
import com.familybank.pesapapipnsystem.exceptions.PaymentMethodNotFoundException;
import com.familybank.pesapapipnsystem.model.PaymentMethod;
import com.familybank.pesapapipnsystem.service.PaymentMethodService;
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
 * @author ekaranja
 */
@RestController
@RequestMapping("/v1/paymentmethod")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/create")
    public ResponseEntity createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        try {
            return paymentMethodService.createPaymentMethod(paymentMethod);
        } catch (PaymentMethodAlreadyExist ex) {
            return ResponseEntity.status(400).body(new ResponseObject(null, ex.getMessage()));
        }
    }

    @GetMapping("/retrieve")
    public ResponseEntity retrievePaymentMethod(@RequestParam(name = "code", required = true) String code) {
        try {
            return paymentMethodService.retrievePaymentMethod(code);
        } catch (PaymentMethodNotFoundException ex) {
            return ResponseEntity.status(404).body(new ResponseObject(null, ex.getMessage()));
        }
    }

}
