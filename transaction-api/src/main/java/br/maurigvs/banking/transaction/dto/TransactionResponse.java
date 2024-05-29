package br.maurigvs.banking.transaction.dto;

import java.time.Instant;

public record TransactionResponse(
        Long id,
        String description,
        Double amount,
        Double balance,
        Instant timestamp
) {
}
