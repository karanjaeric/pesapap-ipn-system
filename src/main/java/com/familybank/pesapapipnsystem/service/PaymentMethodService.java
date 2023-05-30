/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.service;

import com.familybank.pesapapipnsystem.exceptions.PaymentMethodAlreadyExist;
import com.familybank.pesapapipnsystem.exceptions.PaymentMethodNotFoundException;
import com.familybank.pesapapipnsystem.model.PaymentMethod;
import com.familybank.pesapapipnsystem.repository.PaymentMethodRepository;
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
public class PaymentMethodService {
    
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    
    public ResponseEntity createPaymentMethod(PaymentMethod paymentMethod) throws PaymentMethodAlreadyExist {
        if (paymentMethodRepository.findByCode(paymentMethod.getCode()) != null) {
            throw new PaymentMethodAlreadyExist("Payment Method Already Exist");
        }
        paymentMethod.setActive(true);
        paymentMethod = paymentMethodRepository.save(paymentMethod);
        log.info("Payment Method Created Successfully");
        return ResponseEntity.status(200).body(paymentMethod);
        
    }
    
    public ResponseEntity retrievePaymentMethod(String code) throws PaymentMethodNotFoundException {
        PaymentMethod paymentMethod = paymentMethodRepository.findByCode(code);
        if (paymentMethod == null) {
            throw new PaymentMethodNotFoundException("Payment Method Not Found");
        }
        log.info("Payment Method Retrieved Successfully");
        return ResponseEntity.ok().body(paymentMethod);
    }
    
}
