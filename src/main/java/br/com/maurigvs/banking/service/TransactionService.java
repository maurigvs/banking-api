package br.com.maurigvs.banking.service;

import org.springframework.stereotype.Service;

import br.com.maurigvs.banking.model.entity.Account;
import br.com.maurigvs.banking.model.entity.Transaction;
import br.com.maurigvs.banking.repository.TransactionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    protected Transaction create(String description, Double amount, Account account) {
        return repository.save(new Transaction(null, description, amount, account));
    }
}