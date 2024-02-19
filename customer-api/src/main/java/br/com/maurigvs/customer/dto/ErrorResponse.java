package br.com.maurigvs.customer.dto;

public record ErrorResponse(
        String error,
        String message
) {
}
