package com.maurigvs.bank.checkingaccount.model.dto;

import java.util.List;

public record StatementResponse (
        Long accountNumber,
        String dateTime,
        List<StatementItemResponse> movements,
        Double balance
) {
}