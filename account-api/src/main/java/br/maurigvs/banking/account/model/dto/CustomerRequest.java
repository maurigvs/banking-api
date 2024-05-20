package br.maurigvs.banking.account.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(

        @NotBlank
        @Size(min = 11, max = 11, message = "must have 11 numbers")
        String taxId,

        @NotBlank
        String name,

        @NotBlank
        String surname,

        @NotBlank
        @Email
        String emailAddress,

        @NotBlank
        @Size(min = 9, max = 9, message = "must have 9 numbers")
        String phoneNumber,

        @NotBlank
        String birthDate
) {
}
