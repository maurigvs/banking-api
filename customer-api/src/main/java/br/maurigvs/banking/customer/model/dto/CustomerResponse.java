package br.maurigvs.banking.customer.model.dto;

public record CustomerResponse(
        Long id,
        String taxId,
        String name,
        String surname,
        String emailAddress,
        String phoneNumber,
        String birthDate,
        String joinDate
) {
}
