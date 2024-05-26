package br.maurigvs.banking.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerInfo(

        @NotBlank
        @Size(min = 11, max = 11, message = "must have exactly 11 digits")
        String taxId,

        @NotBlank
        String name,

        @NotBlank
        String surname,

        @NotBlank
        String birthDate,
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 9, max = 9, message = "must have exactly 9 digits")
        String phone
) {
}
