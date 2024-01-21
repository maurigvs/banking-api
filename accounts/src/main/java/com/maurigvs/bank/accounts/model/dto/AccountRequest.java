package com.maurigvs.bank.accounts.model.dto;

public record AccountRequest(
        String taxId,
        Integer pinCode
) {
}
