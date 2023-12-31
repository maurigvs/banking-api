package com.maurigvs.bank.accountholder.controller;

import com.maurigvs.bank.accountholder.controller.dto.CreateCompanyRequest;
import com.maurigvs.bank.accountholder.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-holder")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody @Valid CreateCompanyRequest request) throws Exception {
        companyService.createCompany(request);
    }
}