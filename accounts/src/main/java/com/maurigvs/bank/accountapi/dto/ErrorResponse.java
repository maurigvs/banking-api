package com.maurigvs.bank.accountapi.dto;

public record ErrorResponse(
        String error,
        String message
) {
}
