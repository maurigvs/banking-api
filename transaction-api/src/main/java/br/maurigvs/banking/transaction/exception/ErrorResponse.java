package br.maurigvs.banking.transaction.exception;

public record ErrorResponse(
        String error,
        String... message
) {
}
