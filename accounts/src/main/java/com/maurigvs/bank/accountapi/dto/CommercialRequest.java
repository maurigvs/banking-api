package com.maurigvs.bank.accountapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommercialRequest(

        @NotBlank
        @Size(min = 14, max = 14)
        String customerCnpj,

        @Min(value = 100000)
        @Max(value = 999999)
        Integer pinCode
) {
}
