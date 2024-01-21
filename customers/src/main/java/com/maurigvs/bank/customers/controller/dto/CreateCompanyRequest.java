package com.maurigvs.bank.customers.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCompanyRequest(

        @NotBlank(message = "The legal name is required")
        String legalName,

        @NotBlank(message = "The business name is required")
        String businessName,

        @NotBlank(message = "The opening date is required")
        String openingDate,

        @NotBlank(message = "The cnpj is required")
        @Size(min = 14, max = 14, message = "The cnpj must have 14 numbers without any other characters")
        String cnpj,

        @NotBlank(message = "The contact email address is required")
        @Email(message = "The contact email must be an valid address")
        String contactEmail,

        @NotBlank(message = "The contact phone number is required")
        String contactPhoneNumber
) {
}