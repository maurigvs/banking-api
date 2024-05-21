package br.maurigvs.banking.customer.exception;

public record ErrorResponse(
        String error,
        String... message
) {
}
