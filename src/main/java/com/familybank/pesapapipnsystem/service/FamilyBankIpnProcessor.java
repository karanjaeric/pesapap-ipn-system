/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.service;

import com.familybank.pesapapipnsystem.dto.FamilyBankIpnPayload;
import com.familybank.pesapapipnsystem.dto.FamilyBankResponse;
import com.familybank.pesapapipnsystem.exceptions.PaymentAlreadyExistException;
import com.familybank.pesapapipnsystem.model.Channel;
import com.familybank.pesapapipnsystem.model.Payment;
import com.familybank.pesapapipnsystem.model.PaymentMethod;
import com.familybank.pesapapipnsystem.model.Student;
import com.familybank.pesapapipnsystem.repository.ChannelRepository;
import com.familybank.pesapapipnsystem.repository.PaymentMethodRepository;
import com.familybank.pesapapipnsystem.repository.PaymentRepository;
import com.familybank.pesapapipnsystem.repository.StudentRepository;
import java.time.LocalDateTime;
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
public class FamilyBankIpnProcessor {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity processFamilyBankIpn(FamilyBankIpnPayload familyBankIpnPayload) throws PaymentAlreadyExistException {

        log.info("Processing Family Bank Payload {}", familyBankIpnPayload.toString());
        PaymentMethod paymentMethod = paymentMethodRepository.findByCode(familyBankIpnPayload.paymentMethodCode());
        Channel channel = channelRepository.findByCode(familyBankIpnPayload.channelCode());
        Student student = studentRepository.findByRegistrationNumber(familyBankIpnPayload.billReferenceNumber());
        if (paymentRepository.findByBankReferenceNumber(familyBankIpnPayload.bankReferenceNumber()) != null) {
            throw new PaymentAlreadyExistException("Duplicate Payment");
        }
        Payment payment = Payment.builder()
                .accountNumber(familyBankIpnPayload.accountNumber())
                .amount(familyBankIpnPayload.amount())
                .bankReferenceNumber(familyBankIpnPayload.bankReferenceNumber())
                .billReferenceNumber(familyBankIpnPayload.billReferenceNumber())
                .sourceReferenceNumber(familyBankIpnPayload.sourceReferenceNumber())
                .datePaid(LocalDateTime.now())
                .paymentMethodID(paymentMethod == null ? null : paymentMethod.getPaymentMethodID())
                .channelID(channel == null ? null : channel.getChannelID())
                .sourceAccountNumber(familyBankIpnPayload.sourceAccountNumber())
                .studentID(student == null ? null : student.getStudentID())
                .narration(familyBankIpnPayload.narration())
                .build();
        paymentRepository.save(payment);
        log.info("Payment Created Successfully");
        return ResponseEntity.status(200).body(new FamilyBankResponse("00", "Payment Created Successfully"));
    }

}
