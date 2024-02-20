package com.maurigvs.bank.transactionapi.service.impl;

import com.maurigvs.bank.transactionapi.model.Account;
import com.maurigvs.bank.transactionapi.model.Customer;
import com.maurigvs.bank.transactionapi.repository.AccountRepository;
import com.maurigvs.bank.transactionapi.service.AccountService;
import org.springframework.stereotype.Service;

@Service
class AccountServiceImpl implements AccountService {

    // TODO Replace with Grpc Call to account-api
    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    // TODO Replace implementation
    @Override
    public Account findById(Long id) {
        var account = new Account(id, new Customer(1L, "cpf"), 0.00);
        return repository.save(account);
    }
}
