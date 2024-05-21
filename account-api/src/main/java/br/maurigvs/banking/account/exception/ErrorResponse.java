package br.maurigvs.banking.account.exception;

public record ErrorResponse(
        String error,
        String... message
) {
}
