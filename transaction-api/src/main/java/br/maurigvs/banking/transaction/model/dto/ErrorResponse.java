package br.maurigvs.banking.transaction.model.dto;

public record ErrorResponse(
        String error,
        String... message
) {
}
