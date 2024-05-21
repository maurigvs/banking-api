package br.maurigvs.banking.account.grpc;

import br.maurigvs.banking.account.exception.GrpcExceptionHandler;
import br.maurigvs.banking.account.model.dto.TransactionResponse;
import br.maurigvs.banking.account.model.mapper.TransactionMapper;
import br.maurigvs.banking.transaction.grpc.TransactionReply;
import br.maurigvs.banking.transaction.grpc.TransactionRequest;
import br.maurigvs.banking.transaction.grpc.TransactionServiceGrpc;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionGrpcClient {

    private final TransactionServiceGrpc.TransactionServiceStub transactionServiceStub;
    private final TransactionServiceGrpc.TransactionServiceBlockingStub transactionServiceBlockingStub;

    public Mono<Void> create(TransactionRequest request){
        return Mono.fromSupplier(() -> transactionServiceBlockingStub.create(request))
                .then()
                .doOnError(throwable -> log.warn("Error when creating Transaction via Grpc : {}", throwable.getMessage()))
                .onErrorMap(GrpcExceptionHandler::toClientException);
    }

    public Flux<TransactionResponse> findByAccountId(Long id){
        return Flux.create(sink -> transactionServiceStub.findByAccountId(
                Int64Value.newBuilder().setValue(id).build(),
                new TransactionHandler(sink)));
    }

    private record TransactionHandler(FluxSink<TransactionResponse> fluxSink)
        implements StreamObserver<TransactionReply> {

        @Override
        public void onNext(TransactionReply reply) {
            fluxSink.next(TransactionMapper.toResponse(reply));
        }

        @Override
        public void onError(Throwable throwable) {
            log.warn("Error when streaming Transactions via Grpc : {}", throwable.getMessage());
            fluxSink.error(GrpcExceptionHandler.toClientException(throwable));
        }

        @Override
        public void onCompleted() {
            fluxSink.complete();
        }
    }
}
