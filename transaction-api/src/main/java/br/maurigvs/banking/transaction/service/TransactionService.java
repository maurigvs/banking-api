package br.maurigvs.banking.transaction.service;

import br.maurigvs.banking.transaction.model.Transaction;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TransactionService {

    Mono<Transaction> create(Transaction transaction);

    Mono<List<Transaction>> create(List<Transaction> transactionList);
}
