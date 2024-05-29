package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.dto.AccountRequest;
import br.maurigvs.banking.account.dto.AccountResponse;
import br.maurigvs.banking.account.dto.StatementItemInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AggregatorService {

    Mono<AccountResponse> openAccount(AccountRequest request);

    Flux<StatementItemInfo> getAccountStatement(Long accountId);
}
