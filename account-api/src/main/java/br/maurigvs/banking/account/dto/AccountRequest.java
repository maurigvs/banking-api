package br.maurigvs.banking.account.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AccountRequest(

        @NotNull
        @Valid
        CustomerInfo customerInfo,

        @NotNull
        @Positive
        Double initialDeposit,

        @Min(value = 1000, message = "must have exactly 4 digits")
        @Max(value = 9999, message = "must have exactly 4 digits")
        Integer pinCode
) {
}
