package br.maurigvs.banking.account.grpc;

import br.maurigvs.banking.account.exception.GrpcExceptionHandler;
import br.maurigvs.banking.account.mapper.AccountMapper;
import br.maurigvs.banking.account.service.AccountService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountGrpcService extends AccountServiceGrpc.AccountServiceImplBase {

    private final AccountService service;

    @Override
    public void updateBalance(UpdateRequest request, StreamObserver<UpdateReply> response) {

        service.updateBalance(request.getId(), request.getAmount())
                .map(AccountMapper::toUpdateReply)
                .doOnNext(r -> log.info("Account balance updated by Id {}", r.getId()))
                .doOnError(t -> log.warn("Error when updating Account balance: {}", t.getMessage()))
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }
}
