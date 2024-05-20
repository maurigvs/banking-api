package br.maurigvs.banking.transaction.model.dto;

public record TransactionResponse(
        Long id,
        String description,
        Double amount,
        String timestamp
) {
}
