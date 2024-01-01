package com.maurigvs.bank.checkingaccount.model.dto;

public record OpenAccountRequest(
        Long accountHolderId,
        Double initialDeposit,
        Integer pinCode
) {
}
