package com.maurigvs.bank.transactionapi.mapper;

import com.maurigvs.bank.transactionapi.dto.TransactionRequest;
import com.maurigvs.bank.transactionapi.model.Transaction;

import java.time.ZonedDateTime;
import java.util.function.Function;

public class TransactionMapper implements Function<TransactionRequest, Transaction> {

    @Override
    public Transaction apply(TransactionRequest transactionRequest) {
        return new Transaction(null,
                transactionRequest.customerCpf(),
                transactionRequest.accountNumber(),
                transactionRequest.operation(),
                transactionRequest.description(),
                transactionRequest.amount(),
                ZonedDateTime.now());
    }
}
