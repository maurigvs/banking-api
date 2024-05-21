package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.exception.InsufficientFundsException;
import br.maurigvs.banking.account.exception.NotFoundException;
import br.maurigvs.banking.account.model.entity.Account;
import br.maurigvs.banking.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Mono<Account> create(Account account) {
        return Mono.fromSupplier(() -> accountRepository.save(account))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Account> findById(Long id) {
        return Mono.fromSupplier(() -> accountRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(id)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Account> findAll() {
        return Flux.fromStream(accountRepository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Account> updateBalance(Long id, Double amount) {
        return findById(id)
                .filter(account -> account.getBalance() + amount >= 0)
                .switchIfEmpty(Mono.error(new InsufficientFundsException()))
                .map(account -> { account.setBalance(account.getBalance() + amount); return account; })
                .map(accountRepository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
