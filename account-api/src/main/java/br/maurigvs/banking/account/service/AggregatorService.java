package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.model.dto.AccountRequest;
import br.maurigvs.banking.account.model.dto.AccountResponse;
import br.maurigvs.banking.account.model.dto.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AggregatorService {

    Mono<AccountResponse> openAccount(AccountRequest request);

    Flux<TransactionResponse> getAccountStatement(Long accountId);
}
