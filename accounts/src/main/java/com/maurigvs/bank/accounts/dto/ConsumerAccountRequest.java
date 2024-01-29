package com.maurigvs.bank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record ConsumerAccountRequest(

        @NotBlank(message = "taxId is required")
        @Size(min = 11, max = 11, message = "taxId must have 11 digits")
        String taxId,

        @NotNull(message = "pinCode is required")
        @Range(min = 100000, max = 999999, message = "pinCode must have 6 digits")
        Integer pinCode
) {
}
