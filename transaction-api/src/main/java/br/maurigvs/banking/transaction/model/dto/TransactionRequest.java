package br.maurigvs.banking.transaction.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionRequest(

        @NotBlank
        String description,

        @NotNull
        Double amount
) {
}
