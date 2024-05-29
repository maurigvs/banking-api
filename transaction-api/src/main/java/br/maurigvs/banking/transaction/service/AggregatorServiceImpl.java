package br.maurigvs.banking.transaction.service;

import br.maurigvs.banking.transaction.dto.TransactionRequest;
import br.maurigvs.banking.transaction.dto.TransactionResponse;
import br.maurigvs.banking.transaction.grpc.AccountGrpcClient;
import br.maurigvs.banking.transaction.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
class AggregatorServiceImpl implements AggregatorService {

    private final AccountGrpcClient accountService;
    private final TransactionService transactionService;

    @Override
    public Mono<TransactionResponse> process(Long accountId, TransactionRequest request) {

        return accountService.processAmount(accountId, request.amount())
                .map(balance -> TransactionMapper.toEntity(accountId, request, balance))
                .flatMap(transactionService::create)
                .map(TransactionMapper::toResponse);
    }

    @Override
    public Mono<List<TransactionResponse>> transfer(Long senderId, Long recipientId, Double amount) {

        return accountService.transferAmount(senderId, recipientId, amount)
                .map(reply -> TransactionMapper.toEntityList(amount, reply))
                .flatMap(transactionService::create)
                .map(TransactionMapper::toResponseList);
    }
}
