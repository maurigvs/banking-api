package com.maurigvs.bank.checkingaccount.controller;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.service.BankService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class BankController {

    private final BankService service;

    @PostMapping("/open")
    @ResponseStatus(HttpStatus.CREATED)
    public void postAccount(@RequestBody @Valid OpenAccountRequest request) throws BusinessRuleException {
        service.openAccount(request);
    }

    @PostMapping("/{accountId}/{pinCode}/deposit/{amount}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postDeposit(@PathVariable Long accountId,
                            @PathVariable Integer pinCode,
                            @PathVariable Double amount) throws BusinessRuleException {
        service.makeDeposit(accountId, pinCode, amount);
    }

    @PostMapping("/{accountId}/{pinCode}/withdraw/{amount}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postWithdraw(@PathVariable Long accountId,
                            @PathVariable Integer pinCode,
                            @PathVariable Double amount) throws BusinessRuleException {
        service.makeWithdraw(accountId, pinCode, amount);
    }
}