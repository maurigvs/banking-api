package br.maurigvs.banking.account.model.dto;

import jakarta.validation.constraints.NotNull;

public record AccountRequest(

        @NotNull
        Double balance
) {
}
