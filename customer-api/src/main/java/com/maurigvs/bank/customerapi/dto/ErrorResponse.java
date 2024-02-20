package com.maurigvs.bank.customerapi.dto;

public record ErrorResponse(
        String error,
        String message
) {
}
