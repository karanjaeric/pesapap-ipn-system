/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.controller;

import com.familybank.pesapapipnsystem.dto.ResponseObject;
import com.familybank.pesapapipnsystem.exceptions.ChannelNotFoundException;
import com.familybank.pesapapipnsystem.exceptions.PaymentMethodNotFoundException;
import com.familybank.pesapapipnsystem.exceptions.StudentNotFoundException;
import com.familybank.pesapapipnsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ekaranja
 */
@RequestMapping("/v1/payment")
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/retrievebystudent")
    public ResponseEntity retrieveByStudent(@RequestParam(name = "studentRegNo", required = true) String studentRegNo) {
        try {
            return paymentService.retrieveStudentPayments(studentRegNo);
        } catch (StudentNotFoundException ex) {
            return ResponseEntity.status(400).body(new ResponseObject(null, ex.getMessage()));
        }
    }

    @GetMapping("/retrievebypaymentmethod")
    public ResponseEntity retrieveByPaymentMethod(@RequestParam(name = "paymentMethodCode", required = true) String paymentMethodCode) throws ChannelNotFoundException, ChannelNotFoundException {
        try {
            return paymentService.retrievePaymentsByPaymentMethod(paymentMethodCode);
        } catch (PaymentMethodNotFoundException ex) {
            return ResponseEntity.status(400).body(new ResponseObject(null, ex.getMessage()));
        }
    }

    @GetMapping("/retrievebychannel")
    public ResponseEntity retrieveByChannel(@RequestParam(name = "channelCode", required = true) String channelCode) {
        try {
            return paymentService.retrievePaymentsByChannel(channelCode);
        } catch (ChannelNotFoundException ex) {
            return ResponseEntity.status(400).body(new ResponseObject(null, ex.getMessage()));
        }
    }

    @GetMapping("/retrievebychannelandpaymentmethod")
    public ResponseEntity retrieveByChannelAndPaymentMethod(@RequestParam(name = "channelCode", required = true) String channelCode,
            @RequestParam("paymentMethodCode") String paymentMethodCode) {
        try {
            return paymentService.retrievePaymentsByChannelAndPaymentMethod(channelCode, paymentMethodCode);
        } catch (ChannelNotFoundException | PaymentMethodNotFoundException ex) {
            return ResponseEntity.status(400).body(new ResponseObject(null, ex.getMessage()));
        }
    }

}
