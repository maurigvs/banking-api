package br.maurigvs.banking.transaction.controller;

import br.maurigvs.banking.transaction.model.dto.TransactionRequest;
import br.maurigvs.banking.transaction.model.dto.TransactionResponse;
import br.maurigvs.banking.transaction.model.mapper.TransactionMapper;
import br.maurigvs.banking.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TransactionResponse> postTransaction(@RequestBody @Valid TransactionRequest request) {
        return transactionService.create(TransactionMapper.toEntity(request))
                .map(TransactionMapper::toResponse);
    }

    @GetMapping
    public Flux<TransactionResponse> getTransactionList() {
        return transactionService.findAll().map(TransactionMapper::toResponse);
    }
}
