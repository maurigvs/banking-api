package com.maurigvs.bank.transactionapi.service.impl;

import com.maurigvs.bank.transactionapi.model.Transaction;
import com.maurigvs.bank.transactionapi.repository.TransactionRepository;
import com.maurigvs.bank.transactionapi.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Transaction transaction) {
        repository.save(transaction);
    }
}
