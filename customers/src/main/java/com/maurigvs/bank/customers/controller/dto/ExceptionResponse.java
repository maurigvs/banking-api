package com.maurigvs.bank.customers.controller.dto;

import java.util.List;

public record ExceptionResponse(
        String error,
        List<String> detail
) {

    public ExceptionResponse(String error, String detail) {
        this(error, List.of(detail));
    }
}