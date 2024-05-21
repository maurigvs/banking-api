package br.maurigvs.banking.transaction.service;

import br.maurigvs.banking.transaction.model.entity.Transaction;
import br.maurigvs.banking.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Mono<Transaction> create(Transaction transaction) {
        return Mono.fromSupplier(() -> transactionRepository.save(transaction))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Transaction> findByAccountId(Long accountId) {
        return Flux.fromStream(transactionRepository.findByAccountId(accountId).stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Transaction> findAll() {
        return Flux.fromStream(transactionRepository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }
}
