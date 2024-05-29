package br.maurigvs.banking.transaction.controller;

import br.maurigvs.banking.transaction.dto.TransactionRequest;
import br.maurigvs.banking.transaction.dto.TransactionResponse;
import br.maurigvs.banking.transaction.service.AggregatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final AggregatorService service;

    @PostMapping("/account/{accountId}/transaction")
    public Mono<TransactionResponse> postTransaction(@PathVariable Long accountId,
                                                     @RequestBody @Valid TransactionRequest request){
        return service.createTransaction(accountId, request);
    }
}