package com.maurigvs.bank.checkingaccount.model.dto;

public record OpenAccountRequest(
        Long accountHolderId,
        Integer pinCode
) {
}
