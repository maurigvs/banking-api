package com.maurigvs.bank.transactionapi.service;

import com.maurigvs.bank.transactionapi.model.Transaction;
import com.maurigvs.bank.transactionapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Transaction transaction) {
        repository.save(transaction);
    }
}
