/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.controller;

import com.familybank.pesapapipnsystem.dto.FamilyBankIpnPayload;
import com.familybank.pesapapipnsystem.dto.FamilyBankResponse;
import com.familybank.pesapapipnsystem.exceptions.PaymentAlreadyExistException;
import com.familybank.pesapapipnsystem.service.FamilyBankIpnProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ekaranja
 */
@RestController
@RequestMapping("/v1/familybank")
public class FamilyBankIpnController {

    @Autowired
    private FamilyBankIpnProcessor familyBankIpnProcessor;

    @PostMapping("/ipn")
    public ResponseEntity receiveIpn(@RequestBody FamilyBankIpnPayload familyBankIpnPayload) {
        try {
            return familyBankIpnProcessor.processFamilyBankIpn(familyBankIpnPayload);
        } catch (PaymentAlreadyExistException ex) {
            return ResponseEntity.status(200).body(new FamilyBankResponse("00", ex.getMessage()));
        }
    }

}
