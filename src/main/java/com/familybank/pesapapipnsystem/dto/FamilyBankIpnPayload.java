/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.familybank.pesapapipnsystem.dto;

import java.math.BigDecimal;

/**
 *
 * @author ekaranja
 */
public record FamilyBankIpnPayload(
        String accountNumber,
        String billReferenceNumber,
        BigDecimal amount,
        String bankReferenceNumber,
        String sourceReferenceNumber,
        String sourceAccountNumber,
        String narration,
        String paymentMethodCode,
        String channelCode) {

}
