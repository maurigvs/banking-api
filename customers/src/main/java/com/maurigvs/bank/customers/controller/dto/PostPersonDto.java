package com.maurigvs.bank.customers.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostPersonDto(

        @NotBlank(message = "taxId is required")
        @Size(min = 11, max = 11, message = "taxId must have 11 digits")
        String taxId,

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "surname is required")
        String surname,

        @NotBlank(message = "birthDate is required")
        String birthDate,

        @NotBlank(message = "email is required")
        @Email(message = "email must be a well-formed email address")
        String email,

        @NotBlank(message = "phoneNumber is required")
        String phoneNumber
) {
}