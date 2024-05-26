package br.maurigvs.banking.account.dto;

public record AccountResponse(
        Long number,
        Long customerId,
        String openDate
) {
}
