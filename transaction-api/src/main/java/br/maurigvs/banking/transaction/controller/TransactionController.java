package br.maurigvs.banking.transaction.controller;

import br.maurigvs.banking.transaction.dto.TransactionRequest;
import br.maurigvs.banking.transaction.dto.TransactionResponse;
import br.maurigvs.banking.transaction.service.AggregatorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final AggregatorService service;

    @PostMapping("/account/{accountId}/transaction")
    public Mono<TransactionResponse> postTransaction(@PathVariable Long accountId,
                                                     @RequestBody @Valid TransactionRequest request){
        return service.process(accountId, request);
    }

    @PostMapping("/account/{senderId}/transfer/{recipientId}/{amount}")
    public Mono<List<TransactionResponse>> postTransfer(@PathVariable Long senderId,
                                                        @PathVariable Long recipientId,
                                                        @PathVariable @NotNull @Positive Double amount){
        return service.transfer(senderId, recipientId, amount);
    }
}
