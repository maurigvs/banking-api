package com.maurigvs.bank.accountholder.controller.dto;

public record CreatePersonRequest(
        String name,
        String surname,
        String birthDate,
        String cpf,
        String email,
        String phoneNumber
) {
}