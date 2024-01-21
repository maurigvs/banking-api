package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.controller.dto.AccountRequest;
import com.maurigvs.bank.accounts.model.Account;
import com.maurigvs.bank.accounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public void createAccount(AccountRequest request) {
        var account = new Account(null, 1L, 0.0, request.pinCode());
        repository.save(account);
    }
}
