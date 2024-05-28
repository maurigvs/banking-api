package br.maurigvs.banking.transaction.service;

import br.maurigvs.banking.transaction.dto.TransactionRequest;
import br.maurigvs.banking.transaction.dto.TransactionResponse;
import reactor.core.publisher.Mono;

public interface AggregatorService {

    Mono<TransactionResponse> createTransaction(Long accountId, TransactionRequest request);
}
