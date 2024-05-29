package br.maurigvs.banking.transaction.service;

import br.maurigvs.banking.transaction.model.Transaction;
import br.maurigvs.banking.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public Mono<Transaction> create(Transaction transaction) {
        return Mono.fromSupplier(() -> repository.save(transaction))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<List<Transaction>> create(List<Transaction> transactionList) {
        return Mono.fromSupplier(() -> repository.saveAll(transactionList))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
