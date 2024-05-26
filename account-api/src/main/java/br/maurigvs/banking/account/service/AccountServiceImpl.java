package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.model.Account;
import br.maurigvs.banking.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public Mono<Account> create(Account account) {
        return Mono.fromSupplier(() -> repository.save(account))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
