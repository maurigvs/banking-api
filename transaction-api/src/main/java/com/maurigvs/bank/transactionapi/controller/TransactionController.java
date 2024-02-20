package com.maurigvs.bank.transactionapi.controller;

import com.maurigvs.bank.transactionapi.dto.TransactionRequest;
import com.maurigvs.bank.transactionapi.mapper.TransactionMapper;
import com.maurigvs.bank.transactionapi.service.AccountService;
import com.maurigvs.bank.transactionapi.service.CustomerService;
import com.maurigvs.bank.transactionapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final CustomerService customerService;
    private final AccountService accountService;

    public TransactionController(TransactionService transactionService,
                                 CustomerService customerService,
                                 AccountService accountService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postTransaction(@RequestBody @Valid TransactionRequest request){
        var customer = customerService.findByTaxId(request.customerCpf());
        var account = accountService.findById(request.accountNumber());
        var transaction = new TransactionMapper(customer, account).apply(request);
        transactionService.create(transaction);
    }
}
