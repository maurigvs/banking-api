package com.maurigvs.bank.customers.model.dto;

import java.util.List;

public record ApiErrorResponse (
        String error,
        List<String> detail
) {

    public ApiErrorResponse(String error, String message) {
        this(error, List.of(message));
    }
}
