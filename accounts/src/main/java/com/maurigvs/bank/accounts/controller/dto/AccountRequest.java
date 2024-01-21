package com.maurigvs.bank.accounts.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountRequest(

        @NotBlank(message = "taxId is required")
        @Size(min = 14, max = 14, message = "taxId must have 14 digits")
        String taxId,

        @Min(value = 111111, message = "pinCode must have 6 digits")
        @Max(value = 999999, message = "pinCode must have 6 digits")
        Integer pinCode
) {
}
