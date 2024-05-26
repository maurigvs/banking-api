package br.maurigvs.banking.account.dto;

public record CustomerInfo(
        String taxId,
        String name,
        String surname,
        String birthDate,
        String emailAddress,
        String phoneNumber
) {
}
