package br.maurigvs.banking.account.dto;

public record AccountRequest(
        CustomerInfo customerInfo,
        Double initialDeposit,
        Integer pinCode
) {
}
