package com.maurigvs.bank.accountholder.controller.dto;

public record CreatePersonRequest(
        String name,
        String surname,
        String birthDate,
        String taxIdNumber,
        String email,
        String phoneNumber
) {
}