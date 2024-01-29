package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.model.CommercialAccount;
import com.maurigvs.bank.accounts.repository.CommercialAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommercialAccountService implements AccountService<CommercialAccount> {

    private final CommercialAccountRepository repository;

    @Override
    public void create(CommercialAccount account) {
        repository.save(account);
    }
}
