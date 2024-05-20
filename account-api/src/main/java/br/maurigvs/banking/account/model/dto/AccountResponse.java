package br.maurigvs.banking.account.model.dto;

public record AccountResponse(
        Long id,
        Double balance,
        String openDate
) {
}
