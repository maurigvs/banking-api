package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.model.Account;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface AccountService {

    Mono<Account> create(Account account);

    Mono<Account> processAmount(Long id, Double amount);

    Mono<Tuple2<Account, Account>> transferAmount(Long senderId, Long recipientId, Double amount);
}
