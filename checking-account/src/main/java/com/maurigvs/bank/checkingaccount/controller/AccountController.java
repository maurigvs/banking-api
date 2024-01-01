package com.maurigvs.bank.checkingaccount.controller;

import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checking-account/consumer")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postAccount(@RequestBody OpenAccountRequest request){
        accountService.openAccount(request);
    }
}