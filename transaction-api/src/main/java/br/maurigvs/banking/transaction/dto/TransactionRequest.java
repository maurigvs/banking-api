package br.maurigvs.banking.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionRequest(

        @NotBlank
        String description,

        @NotNull
        Double amount
) {
}
