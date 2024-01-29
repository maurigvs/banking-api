package com.maurigvs.bank.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record CommercialAccountRequest(

        @NotBlank(message = "taxId is required")
        @Size(min = 14, max = 14, message = "taxId must have 14 digits")
        String taxId,

        @NotNull(message = "pinCode is required")
        @Range(min = 100000, max = 999999, message = "pinCode must have 6 digits")
        Integer pinCode,

        @NotNull(message = "operator is required")
        @Valid
        CommercialAccountRequest.OperatorRequest operatorRequest
) {

        public record OperatorRequest(

                @NotBlank(message = "operator.name is required")
                String name,

                @NotBlank(message = "operator.email is required")
                String email,

                @NotBlank(message = "operator.phone is required")
                String phone,

                @NotNull(message = "operator.pinCode is required")
                @Range(min = 100000, max = 999999, message = "pinCode must have 6 digits")
                Integer pinCode
        ){

        }
}
