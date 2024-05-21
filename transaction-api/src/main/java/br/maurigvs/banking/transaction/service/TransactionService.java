package br.maurigvs.banking.transaction.service;

import br.maurigvs.banking.transaction.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<Transaction> create(Transaction transaction);
    
    Flux<Transaction> findAll();
}
