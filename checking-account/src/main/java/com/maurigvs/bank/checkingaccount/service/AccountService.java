package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Transactional
    public void openAccount(OpenAccountRequest request) throws BusinessRuleException {

        if(Double.valueOf(0.0).equals(request.initialDeposit()))
            throw new BusinessRuleException("A initial deposit is required to open account");

        final var account = new Account(null, request.accountHolderId(), request.pinCode(), request.initialDeposit());

        accountRepository.save(account);
        transactionService.credit(account, "Initial deposit", request.initialDeposit());
    }

    public Account authenticate(long accoundId, int pinCode) throws BusinessRuleException {
        return accountRepository.findById(accoundId)
                .orElseThrow(() -> new BusinessRuleException("Account not found"));
    }
}