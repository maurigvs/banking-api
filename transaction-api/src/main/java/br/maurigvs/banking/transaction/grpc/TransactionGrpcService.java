package br.maurigvs.banking.transaction.grpc;

import br.maurigvs.banking.transaction.exception.GrpcExceptionHandler;
import br.maurigvs.banking.transaction.model.mapper.TransactionMapper;
import br.maurigvs.banking.transaction.service.TransactionService;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionGrpcService extends TransactionServiceGrpc.TransactionServiceImplBase {

    private final TransactionService transactionService;

    @Override
    public void create(TransactionRequest request, StreamObserver<TransactionReply> response) {
        transactionService.create(TransactionMapper.toEntity(request))
                .map(TransactionMapper::toReply)
                .doOnError(t -> log.error("Error when creating Transaction: {}", t.getMessage()))
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }

    @Override
    public void findByAccountId(Int64Value request, StreamObserver<TransactionReply> response) {
        transactionService.findByAccountId(request.getValue())
                .map(TransactionMapper::toReply)
                .doOnError(t -> log.error("Error when streaming Transactions by Account Id {}: {}", request.getValue(), t.getMessage()))
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }
}
