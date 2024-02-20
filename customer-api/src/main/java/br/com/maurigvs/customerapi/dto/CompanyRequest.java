package br.com.maurigvs.customerapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CompanyRequest(

        @NotBlank
        String businessName,

        @NotBlank
        String legalName,

        @NotBlank
        String cnpj,

        @NotBlank
        String startDate,

        @NotBlank
        @Email
        String emailAddress,

        @NotBlank
        String phoneNumber
) {
}
