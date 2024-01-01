package com.maurigvs.bank.checkingaccount.controller;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.service.AccountService;
import com.maurigvs.bank.checkingaccount.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping("/{accountId}/{pinCode}/deposit/{amount}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postAccount(@PathVariable Long accountId,
                            @PathVariable Integer pinCode,
                            @PathVariable Double amount) throws BusinessRuleException {
        var account = accountService.authenticate(accountId, pinCode);
        transactionService.deposit(account, amount);
        //TODO: Update the account balance after deposit
    }
}