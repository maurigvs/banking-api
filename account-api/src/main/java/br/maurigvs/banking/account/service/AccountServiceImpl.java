package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.exception.BusinessException;
import br.maurigvs.banking.account.exception.InsufficientBalanceException;
import br.maurigvs.banking.account.model.Account;
import br.maurigvs.banking.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public Mono<Account> create(Account account) {
        return Mono.fromSupplier(() -> repository.save(account))
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Account> findById(Long id) {
        return Mono.fromSupplier(() -> repository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Account> processAmount(Long id, Double amount) {
        return findById(id)
                .filter(account -> (account.getBalance() + amount) > 0)
                .switchIfEmpty(Mono.error(new InsufficientBalanceException()))
                .map(account -> { account.setBalance(account.getBalance() + amount); return account; })
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Tuple2<Account, Account>> transferAmount(Long senderId, Long recipientId, Double amount) {
        return findById(senderId).zipWith(findById(recipientId))
                .filter(pair -> !pair.getT1().getId().equals(pair.getT2().getId()))
                .switchIfEmpty(Mono.error(new BusinessException("Sender and recipient can not be the same Account")))
                .filter(pair -> (pair.getT1().getBalance() - amount) > 0)
                .switchIfEmpty(Mono.error(new InsufficientBalanceException()))
                .map(pair -> {
                    var sender = pair.getT1();
                    sender.setBalance(sender.getBalance() - amount);
                    var recipient = pair.getT2();
                    recipient.setBalance(recipient.getBalance() + amount);
                    repository.saveAll(List.of(sender, recipient));
                    return pair;
                })
                .subscribeOn(Schedulers.boundedElastic());
    }
}
