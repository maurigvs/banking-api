package com.maurigvs.bank.accounts.controller;

import com.maurigvs.bank.accounts.dto.ConsumerAccountRequest;
import com.maurigvs.bank.accounts.mapper.ConsumerAccountMapper;
import com.maurigvs.bank.accounts.service.ConsumerAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/consumer")
@RequiredArgsConstructor
public class ConsumerAccountController {

    private final ConsumerAccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postConsumerAccount(@RequestBody @Valid ConsumerAccountRequest request){
        var consumerAccount = new ConsumerAccountMapper().apply(request);
        accountService.create(consumerAccount);
    }
}
