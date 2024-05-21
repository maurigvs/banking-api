package br.maurigvs.banking.account.model.dto;

public record TransactionRequest(
        Long accountId,
        String description,
        Double amount
) {
}
