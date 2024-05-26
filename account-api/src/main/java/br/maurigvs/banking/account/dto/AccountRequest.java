package br.maurigvs.banking.account.dto;

public record AccountRequest(
        Double initialDeposit,
        Integer pinCode
) {
}
