package com.maurigvs.bank.accountapi.controller;

import com.maurigvs.bank.accountapi.dto.ConsumerRequest;
import com.maurigvs.bank.accountapi.mapper.ConsumerMapper;
import com.maurigvs.bank.accountapi.service.ConsumerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService service;

    public ConsumerController(ConsumerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postConsumer(@RequestBody @Valid ConsumerRequest request){
        var consumer = new ConsumerMapper().apply(request);
        service.create(consumer);
    }
}
