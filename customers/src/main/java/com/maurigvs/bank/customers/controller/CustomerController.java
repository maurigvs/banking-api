package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.model.dto.CompanyRequest;
import com.maurigvs.bank.customers.model.dto.PersonRequest;
import com.maurigvs.bank.customers.model.exception.BusinessException;
import com.maurigvs.bank.customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody @Valid PersonRequest request) throws BusinessException {
        customerService.createPerson(request);
    }

    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCompany(@RequestBody CompanyRequest request){
        customerService.createCompany(request);
    }

}
