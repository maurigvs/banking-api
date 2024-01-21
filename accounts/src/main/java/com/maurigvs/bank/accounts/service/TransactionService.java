package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.exception.BusinessRuleException;
import com.maurigvs.bank.accounts.model.entity.Account;

public interface TransactionService {

    void credit(Account account, String description, Double amount) throws BusinessRuleException;

    void debit(Account account, String description, Double amount) throws BusinessRuleException;
}
