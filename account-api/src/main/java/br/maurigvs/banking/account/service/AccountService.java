package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.model.Account;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<Account> create(Account account);

    Mono<Account> updateBalance(Long id, Double amount);
}
