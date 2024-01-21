package com.maurigvs.bank.customers.controller.dto;

import java.util.List;

public record ExceptionDto(
        String error,
        List<String> detail
) {

    public ExceptionDto(String error, String detail) {
        this(error, List.of(detail));
    }
}