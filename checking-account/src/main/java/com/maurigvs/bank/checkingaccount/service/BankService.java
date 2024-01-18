package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;

public interface BankService {

    void openAccount(OpenAccountRequest request) throws BusinessRuleException;

    void makeDeposit(Long accountId, Integer pinCode, Double amount) throws BusinessRuleException;

    void makeWithdraw(Long accountId, Integer pinCode, Double amount) throws BusinessRuleException;
}