package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.model.Account;
import br.maurigvs.banking.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public Mono<Account> create(Account account) {
        return Mono.fromSupplier(() -> repository.save(account))
                .doOnNext(r -> log.info("Account created with id: {}", r.getId()))
                .doOnError(t -> log.warn("Error creating Account: {}", t.getMessage()))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
