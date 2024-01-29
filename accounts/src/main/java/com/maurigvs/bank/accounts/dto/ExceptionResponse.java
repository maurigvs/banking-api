package com.maurigvs.bank.accounts.dto;

import java.util.List;

public record ExceptionResponse(
        String error,
        List<String> detail
) {
}