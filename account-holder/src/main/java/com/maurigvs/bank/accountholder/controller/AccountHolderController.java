package com.maurigvs.bank.accountholder.controller;

import com.maurigvs.bank.accountholder.controller.dto.CreateCompanyRequest;
import com.maurigvs.bank.accountholder.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.accountholder.service.CompanyService;
import com.maurigvs.bank.accountholder.service.PersonService;
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
public class AccountHolderController {

    private final CompanyService companyService;
    private final PersonService personService;

    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody CreateCompanyRequest request) throws Exception {
        companyService.createCompany(request);
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody CreatePersonRequest request) throws Exception {
        personService.createPerson(request);
    }
}