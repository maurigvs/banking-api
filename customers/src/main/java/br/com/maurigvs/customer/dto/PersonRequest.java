package br.com.maurigvs.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PersonRequest(

        @NotBlank
        String name,

        @NotBlank
        String surname,

        @NotBlank
        String cpf,

        @NotBlank
        String birthDate,

        @NotBlank
        @Email
        String emailAddress,

        @NotBlank
        String phoneNumber
) {
}
