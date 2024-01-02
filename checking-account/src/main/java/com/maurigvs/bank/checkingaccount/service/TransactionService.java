package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.entity.Account;

public interface TransactionService {

    void credit(Account account, String description, Double amount) throws BusinessRuleException;
}
