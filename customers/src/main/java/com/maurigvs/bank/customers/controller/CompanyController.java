package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.dto.CompanyRequest;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.mapper.CompanyMapper;
import com.maurigvs.bank.customers.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody @Valid CompanyRequest request) throws BusinessException {
        var company = new CompanyMapper().apply(request);
        service.create(company);
    }
}