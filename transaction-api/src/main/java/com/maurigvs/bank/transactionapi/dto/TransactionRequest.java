package com.maurigvs.bank.transactionapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionRequest(

        @NotBlank
        String customerCpf,

        @NotNull
        Long accountNumber,

        @NotBlank
        String operation,

        @NotBlank
        String description,

        @NotNull
        Double amount
) {
}
