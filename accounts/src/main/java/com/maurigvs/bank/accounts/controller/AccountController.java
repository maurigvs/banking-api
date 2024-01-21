package com.maurigvs.bank.accounts.controller;

import com.maurigvs.bank.accounts.model.dto.AccountRequest;
import com.maurigvs.bank.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/account/commercial")
    @ResponseStatus(HttpStatus.CREATED)
    public void postAccount(@RequestBody AccountRequest request){
        accountService.openAccount(request);
    }
}
