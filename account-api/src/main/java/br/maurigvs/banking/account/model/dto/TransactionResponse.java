package br.maurigvs.banking.account.model.dto;

public record TransactionResponse(
        Long id,
        String description,
        Double amount,
        String timestamp
) {
}
