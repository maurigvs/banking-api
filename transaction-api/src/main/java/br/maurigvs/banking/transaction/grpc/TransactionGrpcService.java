package br.maurigvs.banking.transaction.grpc;

import br.maurigvs.banking.transaction.exception.GrpcExceptionHandler;
import br.maurigvs.banking.transaction.mapper.TransactionMapper;
import br.maurigvs.banking.transaction.service.TransactionService;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionGrpcService extends TransactionServiceGrpc.TransactionServiceImplBase {

    private final TransactionService service;

    @Override
    public void findByAccount(Int64Value request, StreamObserver<TransactionReply> response) {
        service.findByAccountId(request.getValue())
                .map(TransactionMapper::toReply)
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }
}
