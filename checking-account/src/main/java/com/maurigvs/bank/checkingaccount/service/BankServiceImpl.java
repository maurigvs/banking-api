package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Transactional
    @Override
    public void openAccount(OpenAccountRequest request) throws BusinessRuleException {
        accountService.checkElegibility(request);
        var account = accountService.openAccount(request);
        creditAccount(account, "Initial deposit", request.initialDeposit());
    }

    @Transactional
    @Override
    public void makeDeposit(Long accountId, Integer pinCode, Double amount) throws BusinessRuleException {
        var account = accountService.authenticate(accountId, pinCode);
        creditAccount(account, "Cash Deposit", amount);
    }

    private void creditAccount(Account account, String description, Double amount) throws BusinessRuleException {
        transactionService.credit(account, description, amount);
        accountService.updateBalance(account, amount);
    }
}