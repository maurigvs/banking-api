package com.maurigvs.bank.accounts.controller;

import com.maurigvs.bank.accounts.dto.CommercialAccountRequest;
import com.maurigvs.bank.accounts.mapper.CommercialAccountMapper;
import com.maurigvs.bank.accounts.service.CommercialAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/commercial")
@RequiredArgsConstructor
public class CommercialAccountController {

    private final CommercialAccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCommercialAccount(@RequestBody @Valid CommercialAccountRequest request){
        var commercialAccount = new CommercialAccountMapper().apply(request);
        accountService.create(commercialAccount);
    }
}
