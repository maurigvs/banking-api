package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.exception.BusinessRuleException;
import com.maurigvs.bank.accounts.model.dto.OpenAccountRequest;

public interface BankService {

    void openAccount(OpenAccountRequest request) throws BusinessRuleException;

    void makeDeposit(Long accountId, Integer pinCode, Double amount) throws BusinessRuleException;

    void makeWithdraw(Long accountId, Integer pinCode, Double amount) throws BusinessRuleException;
}