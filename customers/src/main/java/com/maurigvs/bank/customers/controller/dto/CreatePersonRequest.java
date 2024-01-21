package com.maurigvs.bank.customers.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePersonRequest(

        @NotBlank(message = "The name is required")
        String name,

        @NotBlank(message = "The surname is required")
        String surname,

        @NotBlank(message = "The birth date is required")
        String birthDate,

        @NotBlank(message = "The cpf is required")
        @Size(min = 11, max = 11, message = "The cpf must have 11 numbers without any other characters")
        String cpf,

        @NotBlank(message = "The email address is required")
        String email,

        @NotBlank(message = "The phone number is required")
        String phoneNumber
) {
}