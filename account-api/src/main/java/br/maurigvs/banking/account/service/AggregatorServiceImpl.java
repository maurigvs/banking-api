package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.grpc.CustomerGrpcClient;
import br.maurigvs.banking.account.grpc.TransactionGrpcClient;
import br.maurigvs.banking.account.model.dto.AccountRequest;
import br.maurigvs.banking.account.model.dto.AccountResponse;
import br.maurigvs.banking.account.model.dto.TransactionResponse;
import br.maurigvs.banking.account.model.entity.Account;
import br.maurigvs.banking.account.model.mapper.AccountMapper;
import br.maurigvs.banking.account.model.mapper.CustomerMapper;
import br.maurigvs.banking.account.model.mapper.TransactionMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class AggregatorServiceImpl implements AggregatorService {

    private final AccountService accountService;
    private final CustomerGrpcClient customerGrpcClient;
    private final TransactionGrpcClient transactionGrpcClient;

    @Override
    public Mono<AccountResponse> openAccount(AccountRequest request) {
        return Mono.just(new OpenAccountContext(request))
                .flatMap(this::createCustomer)
                .flatMap(this::createAccount)
                .flatMap(this::createTransaction)
                .flatMap(this::updateBalance)
                .map(OpenAccountContext::getAccount)
                .map(AccountMapper::toResponse);
    }

    @Override
    public Flux<TransactionResponse> getAccountStatement(Long accountId) {
        return accountService.findById(accountId).flatMapMany(account ->
                transactionGrpcClient.findByAccountId(accountId));
    }

    private Mono<OpenAccountContext> createCustomer(OpenAccountContext context){
        return customerGrpcClient.create(CustomerMapper.toRequest(context.getRequest().customerInfo()))
                .doOnNext(context::setCustomerId)
                .thenReturn(context);
    }

    private Mono<OpenAccountContext> createAccount(OpenAccountContext context){
        return accountService.create(AccountMapper.toEntity(context.getRequest(), context.getCustomerId()))
                .doOnNext(context::setAccount)
                .thenReturn(context);
    }

    private Mono<OpenAccountContext> createTransaction(OpenAccountContext context){
        return transactionGrpcClient.create(TransactionMapper.toRequest(context.getAccount(), context.getRequest()))
                .thenReturn(context);
    }

    private Mono<OpenAccountContext> updateBalance(OpenAccountContext context){
        return accountService.updateBalance(context.getAccount().getId(), context.getRequest().initialDeposit())
                .doOnNext(context::setAccount)
                .thenReturn(context);
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    private static class OpenAccountContext {
        private final AccountRequest request;
        private Long customerId;
        private Account account;
    }
}
