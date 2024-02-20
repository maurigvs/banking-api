package br.com.maurigvs.customerapi.dto;

public record ErrorResponse(
        String error,
        String message
) {
}
