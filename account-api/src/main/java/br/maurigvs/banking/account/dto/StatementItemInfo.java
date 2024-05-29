package br.maurigvs.banking.account.dto;

import java.time.Instant;

public record StatementItemInfo(
        Instant timestamp,
        String description,
        Long refCode,
        Double amount,
        Double balance
) {
}
