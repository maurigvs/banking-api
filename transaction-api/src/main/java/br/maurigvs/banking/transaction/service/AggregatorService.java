package br.maurigvs.banking.transaction.service;

import br.maurigvs.banking.transaction.dto.TransactionRequest;
import br.maurigvs.banking.transaction.dto.TransactionResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AggregatorService {

    Mono<TransactionResponse> process(Long accountId, TransactionRequest request);

    Mono<List<TransactionResponse>> transfer(Long senderId, Long recipientId, Double amount);
}
