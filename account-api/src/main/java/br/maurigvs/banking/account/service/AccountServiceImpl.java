package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.exception.BusinessException;
import br.maurigvs.banking.account.model.Account;
import br.maurigvs.banking.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

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

    private Mono<Account> findById(Long id) {
        return Mono.fromSupplier(() -> repository.findById(id))
                .filter(Optional::isPresent)
                .switchIfEmpty(Mono.error(new BusinessException("Account not found by Id "+ id)))
                .map(Optional::get)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Account> updateBalance(Long id, Double amount) {
        return findById(id)
                .filter(account -> (account.getBalance() + amount) > 0)
                .switchIfEmpty(Mono.error(new BusinessException("Account balance is insufficient")))
                .map(account -> { account.setBalance(account.getBalance() + amount); return account; })
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
