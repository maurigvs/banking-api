package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.model.entity.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<Account> create(Account account);

    Mono<Account> findById(Long id);

    Flux<Account> findAll();

    Mono<Account> updateBalance(Long id, Double amount);
}
