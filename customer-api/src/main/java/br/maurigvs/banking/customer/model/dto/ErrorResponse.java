package br.maurigvs.banking.customer.model.dto;

public record ErrorResponse(
        String error,
        String... message
) {
}
