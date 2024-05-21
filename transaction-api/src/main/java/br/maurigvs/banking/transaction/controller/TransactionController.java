package br.maurigvs.banking.transaction.controller;

import br.maurigvs.banking.transaction.model.dto.TransactionResponse;
import br.maurigvs.banking.transaction.model.mapper.TransactionMapper;
import br.maurigvs.banking.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public Flux<TransactionResponse> getTransactionList() {
        return transactionService.findAll().map(TransactionMapper::toResponse);
    }
}
