package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.controller.dto.PostPersonDto;
import com.maurigvs.bank.customers.service.PersonService;
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
public class PersonController {

    private final PersonService personService;

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody @Valid PostPersonDto request) throws Exception {
        personService.createPerson(request);
    }
}