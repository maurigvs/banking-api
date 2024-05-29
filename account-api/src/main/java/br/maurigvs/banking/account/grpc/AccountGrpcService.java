package br.maurigvs.banking.account.grpc;

import br.maurigvs.banking.account.exception.GrpcExceptionHandler;
import br.maurigvs.banking.account.mapper.AccountMapper;
import br.maurigvs.banking.account.service.AccountService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountGrpcService extends AccountServiceGrpc.AccountServiceImplBase {

    private final AccountService service;

    @Override
    public void processAmount(UpdateRequest request, StreamObserver<UpdateReply> response) {

        service.processAmount(request.getId(), request.getAmount())
                .map(AccountMapper::toUpdateReply)
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }

    @Override
    public void transferAmount(TransferRequest request, StreamObserver<TransferReply> response) {

        service.transferAmount(request.getId(), request.getRecipient().getId(), request.getRecipient().getAmount())
                .map(AccountMapper::toTransferResponse)
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }
}
