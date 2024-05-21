package br.maurigvs.banking.account.model.mapper;

import br.maurigvs.banking.account.model.dto.AccountRequest;
import br.maurigvs.banking.account.model.dto.AccountResponse;
import br.maurigvs.banking.account.model.entity.Account;

import java.time.LocalDate;

public final class AccountMapper {

    public static Account toEntity(AccountRequest request, Long customerId){
        return  new Account(null,
                customerId,
                0.0,
                LocalDate.now());
    }

    public static AccountResponse toResponse(Account account){
        return new AccountResponse(
                account.getId(),
                account.getBalance(),
                account.getOpenDate().toString());
    }
}
