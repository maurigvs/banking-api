package com.maurigvs.bank.transactionapi.mapper;

import com.maurigvs.bank.transactionapi.dto.TransactionRequest;
import com.maurigvs.bank.transactionapi.model.Account;
import com.maurigvs.bank.transactionapi.model.Customer;
import com.maurigvs.bank.transactionapi.model.Transaction;

import java.time.ZonedDateTime;
import java.util.function.Function;

public class TransactionMapper implements Function<TransactionRequest, Transaction> {

    private final Customer customer;
    private final Account account;

    public TransactionMapper(Customer customer, Account account) {
        this.customer = customer;
        this.account = account;
    }

    @Override
    public Transaction apply(TransactionRequest request) {
        return new Transaction(null,
                customer,
                account,
                request.operation(),
                request.description(),
                request.amount(),
                ZonedDateTime.now(),
                false);
    }
}
