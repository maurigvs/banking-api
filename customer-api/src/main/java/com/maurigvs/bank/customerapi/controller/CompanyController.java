package com.maurigvs.bank.customerapi.controller;

import com.maurigvs.bank.customerapi.dto.CompanyRequest;
import com.maurigvs.bank.customerapi.mapper.CompanyMapper;
import com.maurigvs.bank.customerapi.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody @Valid CompanyRequest request){
        var company = new CompanyMapper().apply(request);
        service.create(company);
    }
}
