package br.maurigvs.banking.account.model.dto;

public record ErrorResponse(
        String error,
        String... message
) {
}
