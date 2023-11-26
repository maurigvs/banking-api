package br.com.maurigvs.banking.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.maurigvs.banking.model.Account;
import lombok.Getter;

@Getter
public class AccountResponse {

    private final String accountId;
    
    private final CustomerResponse customer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate openedAt;

    public AccountResponse(Account a) {
        this.accountId = a.getKeyCode().toString();
        this.customer = new CustomerResponse(a.getCustomer());
        this.openedAt = a.getOpenedAt();
    }
}