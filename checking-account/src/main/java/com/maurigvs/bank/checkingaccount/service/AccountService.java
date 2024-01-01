package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void openAccount(OpenAccountRequest request) {
        accountRepository.save(new Account(null, request.accountHolderId(), request.pinCode()));
    }
}