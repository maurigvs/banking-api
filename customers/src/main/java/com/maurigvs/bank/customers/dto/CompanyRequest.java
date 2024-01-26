package com.maurigvs.bank.customers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyRequest(

        @NotBlank(message = "taxId is required")
        @Size(min = 14, max = 14, message = "taxId must have 14 digits")
        String taxId,

        @NotBlank(message = "businessName is required")
        String businessName,

        @NotBlank(message = "legalName is required")
        String legalName,

        @NotBlank(message = "startDate is required")
        String startDate,

        @NotBlank(message = "email is required")
        @Email(message = "email must be a well-formed email address")
        String email,

        @NotBlank(message = "phoneNumber is required")
        String phoneNumber
) {
}