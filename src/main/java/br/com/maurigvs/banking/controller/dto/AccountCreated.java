package br.com.maurigvs.banking.controller.dto;

import br.com.maurigvs.banking.model.Account;
import lombok.Getter;

@Getter
public class AccountCreated {

    private final String accountId;

    public AccountCreated(Account account){
        this.accountId = account.getKeyCode().toString();
    }
}