package br.com.maurigvs.banking.model.dto;

import br.com.maurigvs.banking.model.entity.Account;
import lombok.Getter;

@Getter
public class AccountCreated {

    private final String accountId;

    public AccountCreated(Account account){
        this.accountId = account.getKeyCode().toString();
    }
}