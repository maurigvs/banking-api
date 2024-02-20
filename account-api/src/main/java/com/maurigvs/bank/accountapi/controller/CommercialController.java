package com.maurigvs.bank.accountapi.controller;

import com.maurigvs.bank.accountapi.dto.CommercialRequest;
import com.maurigvs.bank.accountapi.mapper.CommercialMapper;
import com.maurigvs.bank.accountapi.service.CommercialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commercial")
public class CommercialController {

    private final CommercialService service;

    public CommercialController(CommercialService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCommercial(@RequestBody @Valid CommercialRequest request){
        var commercial = new CommercialMapper().apply(request);
        service.create(commercial);
    }
}
