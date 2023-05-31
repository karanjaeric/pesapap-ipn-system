/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.service;

import com.familybank.pesapapipnsystem.dto.ResponseObject;
import com.familybank.pesapapipnsystem.exceptions.ChannelNotFoundException;
import com.familybank.pesapapipnsystem.exceptions.PaymentMethodNotFoundException;
import com.familybank.pesapapipnsystem.exceptions.StudentNotFoundException;
import com.familybank.pesapapipnsystem.model.Channel;
import com.familybank.pesapapipnsystem.model.Payment;
import com.familybank.pesapapipnsystem.model.PaymentMethod;
import com.familybank.pesapapipnsystem.model.Student;
import com.familybank.pesapapipnsystem.repository.ChannelRepository;
import com.familybank.pesapapipnsystem.repository.PaymentMethodRepository;
import com.familybank.pesapapipnsystem.repository.PaymentRepository;
import com.familybank.pesapapipnsystem.repository.StudentRepository;
import java.util.List;
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
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private ChannelRepository channelRepository;

    public ResponseEntity retrieveStudentPayments(String studentRegistrationNumber) throws StudentNotFoundException {
        Student student = studentRepository.findByRegistrationNumber(studentRegistrationNumber);
        if (student == null) {
            throw new StudentNotFoundException("Student with RegNO " + studentRegistrationNumber + " Does Not Exist");
        }
        List<Payment> payments = paymentRepository.findByStudentID(student.getStudentID());
        return ResponseEntity.status(200).body(new ResponseObject(payments, "Payments Retrieved Successfully"));
    }

    public ResponseEntity retrievePaymentsByPaymentMethod(String paymentMethodCode) throws PaymentMethodNotFoundException {
        PaymentMethod paymentMethod = paymentMethodRepository.findByCode(paymentMethodCode);
        if (paymentMethod == null) {
            throw new PaymentMethodNotFoundException("Payment Method " + paymentMethodCode + " Does Not Exist");
        }
        List<Payment> payments = paymentRepository.findByPaymentMethodID(paymentMethod.getPaymentMethodID());
        return ResponseEntity.status(200).body(new ResponseObject(payments, "Payments Retrieved Successfully"));
    }

    public ResponseEntity retrievePaymentsByChannel(String channelCode) throws ChannelNotFoundException {
        Channel channel = channelRepository.findByCode(channelCode);
        if (channel == null) {
            throw new ChannelNotFoundException("Channel " + channelCode + " Does Not Exist");
        }
        List<Payment> payments = paymentRepository.findByChannelID(channel.getChannelID());
        return ResponseEntity.status(200).body(new ResponseObject(payments, "Payments Retrieved Successfully"));
    }

    public ResponseEntity retrievePaymentsByChannelAndPaymentMethod(String channelCode, String paymentMethodCode) throws ChannelNotFoundException, PaymentMethodNotFoundException {
        Channel channel = channelRepository.findByCode(channelCode);
        if (channel == null) {
            throw new ChannelNotFoundException("Channel " + channelCode + " Does Not Exist");
        }
        PaymentMethod paymentMethod = paymentMethodRepository.findByCode(paymentMethodCode);
        if (paymentMethod == null) {
            throw new PaymentMethodNotFoundException("Payment Method " + paymentMethodCode + " Does Not Exist");
        }
        List<Payment> payments = paymentRepository.findByChannelIDAndPaymentMethodID(channel.getChannelID(), paymentMethod.getPaymentMethodID());
        return ResponseEntity.status(200).body(new ResponseObject(payments, "Payments Retrieved Successfully"));
    }

}
