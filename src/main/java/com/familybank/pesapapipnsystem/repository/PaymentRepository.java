/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.repository;

import com.familybank.pesapapipnsystem.model.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author erick.karanja_cellul
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByBankReferenceNumber(String bankReferenceNumber);

    List<Payment> findByStudentID(Long studentID);

    List<Payment> findByPaymentMethodID(Long paymentMethodID);

    List<Payment> findByChannelID(Long channelID);

    List<Payment> findByChannelIDAndPaymentMethodID(Long channelID, Long paymentMethodID);
}
