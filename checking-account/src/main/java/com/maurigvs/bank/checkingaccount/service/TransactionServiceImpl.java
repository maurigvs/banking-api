package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.model.entity.Transaction;
import com.maurigvs.bank.checkingaccount.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public void credit(Account account, String description, Double amount) throws BusinessRuleException {

        if(Double.compare(0.0, amount) > 0)
            throw new BusinessRuleException("Transaction denied");

        repository.save(new Transaction(null, description, amount, account));
    }

    @Override
    public void debit(Account account, String description, Double amount) {
        if(Double.compare(0.0, amount) < 0) amount = amount * -1;
        repository.save(new Transaction(null, description, amount, account));
    }
}