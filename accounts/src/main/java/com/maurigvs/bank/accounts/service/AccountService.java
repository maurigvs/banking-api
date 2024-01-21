package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.exception.AuthenticationException;
import com.maurigvs.bank.accounts.exception.BusinessRuleException;
import com.maurigvs.bank.accounts.model.dto.OpenAccountRequest;
import com.maurigvs.bank.accounts.model.entity.Account;

public interface AccountService {

    Account authenticate(long accoundId, int pinCode) throws AuthenticationException;

    void checkElegibility(OpenAccountRequest request) throws BusinessRuleException;

    Account openAccount(OpenAccountRequest request);

    void creditAmount(Account account, Double amount);

    void debitAmount(Account account, Double amount);
}
