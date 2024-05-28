package br.maurigvs.banking.transaction.grpc;

import br.maurigvs.banking.account.grpc.AccountServiceGrpc;
import br.maurigvs.banking.account.grpc.UpdateReply;
import br.maurigvs.banking.account.grpc.UpdateRequest;
import br.maurigvs.banking.transaction.exception.GrpcExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountGrpcClient {

    private final AccountServiceGrpc.AccountServiceBlockingStub accountServiceBlockingStub;

    public Mono<Double> updateBalance(Long id, Double amount){
        return Mono.just(UpdateRequest.newBuilder().setId(id).setAmount(amount).build())
                .map(accountServiceBlockingStub::updateBalance)
                .map(UpdateReply::getBalance)
                .onErrorMap(GrpcExceptionHandler::toClientException);
    }
}
