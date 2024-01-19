package com.maurigvs.bank.customers.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonRequest(

        @NotBlank(message = "taxId must not be blank")
        @Size(min = 11, max = 11, message = "taxId must not be blank and must have 11 digits")
        String taxId,

        @NotBlank(message = "name must not be blank")
        String name,

        @NotBlank(message = "surname must not be blank")
        String surname,

        @NotBlank(message = "birthDate must not be blank")
        String birthDate,

        @NotBlank(message = "email must not be blank")
        @Email(message = "email must be a well-formed email address")
        String email,

        @NotBlank(message = "phone must not be blank")
        @Size(min = 9, max = 9, message = "phone must not be blank and must have 9 digits")
        String phone
) {
}
