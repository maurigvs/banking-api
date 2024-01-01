package com.maurigvs.bank.checkingaccount.model.dto;

public record ErrorResponse(
        String error,
        String message
) {
}