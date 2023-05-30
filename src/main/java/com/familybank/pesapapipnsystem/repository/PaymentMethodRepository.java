/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.familybank.pesapapipnsystem.repository;

import com.familybank.pesapapipnsystem.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ekaranja
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    PaymentMethod findByCode(String code);
}
