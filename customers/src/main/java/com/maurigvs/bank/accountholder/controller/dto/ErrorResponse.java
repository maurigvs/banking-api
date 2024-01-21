package com.maurigvs.bank.accountholder.controller.dto;

import java.util.List;

public record ErrorResponse(
        String error,
        List<String> messages
) {

    public ErrorResponse(String error, String message) {
        this(error, List.of(message));
    }
}