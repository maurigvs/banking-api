package com.maurigvs.bank.customers.model.dto;

public record CompanyRequest(
        String taxId,
        String businessName,
        String legalName,
        String startDate,
        String contactEmail,
        String contactPhone
) {
}
