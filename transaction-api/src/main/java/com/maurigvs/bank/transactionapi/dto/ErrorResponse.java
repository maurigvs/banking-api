package com.maurigvs.bank.transactionapi.dto;

public record ErrorResponse(
        String error,
        String message
) {
}
