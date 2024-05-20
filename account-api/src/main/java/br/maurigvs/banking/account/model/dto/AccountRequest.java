package br.maurigvs.banking.account.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AccountRequest(

        @NotNull
        @Valid
        CustomerRequest customerInfo,

        @NotNull
        Double balance
) {
}
