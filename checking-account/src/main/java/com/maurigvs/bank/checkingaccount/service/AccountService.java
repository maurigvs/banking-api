package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.AuthenticationException;
import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.entity.Account;

public interface AccountService {

    Account authenticate(long accoundId, int pinCode) throws AuthenticationException;

    void checkElegibility(OpenAccountRequest request) throws BusinessRuleException;

    Account openAccount(OpenAccountRequest request);

    void updateBalance(Account account, Double amount);
}
