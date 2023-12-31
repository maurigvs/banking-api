package com.maurigvs.bank.accountholder.controller.dto;

public record CreateCompanyRequest(
        String legalName,
        String businessName,
        String openingDate,
        String taxIdNumber,
        String contactEmail,
        String contactPhoneNumber
) {
}