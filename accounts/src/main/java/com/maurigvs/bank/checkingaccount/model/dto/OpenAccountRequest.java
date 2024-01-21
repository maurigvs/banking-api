package com.maurigvs.bank.checkingaccount.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OpenAccountRequest(

        @NotNull(message = "The account holder id is required")
        Long accountHolderId,

        @NotNull(message = "The initial deposit is required")
        Double initialDeposit,

        @NotNull(message = "The pin code is required")
        @Min(value = 111111, message = "The pin code must have 6 digits.")
        @Max(value = 999999, message = "The pin code must have 6 digits.")
        Integer pinCode
) {
}