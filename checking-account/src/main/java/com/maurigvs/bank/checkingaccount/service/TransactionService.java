package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.StatementItemResponse;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.model.entity.Transaction;

import java.util.List;

public interface TransactionService {

    void credit(Account account, String description, Double amount) throws BusinessRuleException;

    void debit(Account account, String description, Double amount) throws BusinessRuleException;

    List<StatementItemResponse> parseToStatementItensList(List<Transaction> transactions);
}
