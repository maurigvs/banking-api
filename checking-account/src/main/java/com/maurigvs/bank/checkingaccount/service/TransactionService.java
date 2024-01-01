package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.model.entity.Transaction;
import com.maurigvs.bank.checkingaccount.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public void credit(Account account, String description, Double amount) {
        transactionRepository.save(new Transaction(null, description, amount, account));
    }

    public void deposit(Account account, Double amount) {
        credit(account, "Cash deposit", amount);
    }
}