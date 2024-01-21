package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.exception.AuthenticationException;
import com.maurigvs.bank.accounts.exception.BusinessRuleException;
import com.maurigvs.bank.accounts.model.dto.OpenAccountRequest;
import com.maurigvs.bank.accounts.model.entity.Account;
import com.maurigvs.bank.accounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public Account authenticate(long accoundId, int pinCode) throws AuthenticationException {
        var account = repository.findById(accoundId)
                .orElseThrow(() -> new AuthenticationException("Account not found"));
        if(!account.getPinCode().equals(pinCode))
            throw new AuthenticationException("Authentication failed");
        return account;
    }

    @Override
    public void checkElegibility(OpenAccountRequest request) throws BusinessRuleException {
        if(Double.valueOf(0.0).equals(request.initialDeposit()))
            throw new BusinessRuleException("A initial deposit is required to open account");
    }

    @Override
    public Account openAccount(OpenAccountRequest request) {
        var account = new Account(null, request.accountHolderId(), request.pinCode(), 0.0);
        return repository.save(account);
    }

    @Override
    public void creditAmount(Account account, Double amount) {
        repository.getReferenceById(account.getId()).updateBalance(amount);
    }

    @Override
    public void debitAmount(Account account, Double amount) {
        repository.getReferenceById(account.getId()).updateBalance(amount * -1);
    }
}