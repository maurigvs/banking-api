package br.maurigvs.banking.account.mapper;

import br.maurigvs.banking.account.dto.AccountRequest;
import br.maurigvs.banking.account.dto.AccountResponse;
import br.maurigvs.banking.account.model.Account;

import java.time.LocalDate;

public final class AccountMapper {

    public static Account toEntity(AccountRequest request){
        return new Account(null,
                request.initialDeposit(),
                request.pinCode(),
                LocalDate.now());
    }

    public static AccountResponse toResponse(Account account){
        return new AccountResponse(
                account.getId(),
                account.getOpenDate().toString());
    }
}