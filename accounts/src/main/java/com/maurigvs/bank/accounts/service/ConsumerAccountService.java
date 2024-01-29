package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.model.ConsumerAccount;
import com.maurigvs.bank.accounts.repository.ConsumerAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerAccountService implements AccountService<ConsumerAccount> {

    private final ConsumerAccountRepository repository;
    
    @Override
    public void create(ConsumerAccount account) {
        repository.save(account);
    }
}
