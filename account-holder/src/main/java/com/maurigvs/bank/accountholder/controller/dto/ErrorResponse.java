package com.maurigvs.bank.accountholder.controller.dto;

public record ErrorResponse(
        String error,
        String message
) {
}