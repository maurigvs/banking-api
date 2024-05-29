package br.maurigvs.banking.account.grpc;

import br.maurigvs.banking.account.exception.GrpcExceptionHandler;
import br.maurigvs.banking.transaction.grpc.TransactionReply;
import br.maurigvs.banking.transaction.grpc.TransactionServiceGrpc;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
@RequiredArgsConstructor
public class TransactionGrpcClient {

    private final TransactionServiceGrpc.TransactionServiceStub transactionServiceStub;
    
    public Flux<TransactionReply> findByAccountId(Long accountId){
        return Flux.<TransactionReply>create(sink -> transactionServiceStub.findByAccount(
                        Int64Value.newBuilder().setValue(accountId).build(),
                        new TransactionHandler(sink)))
                .onErrorMap(GrpcExceptionHandler::toClientException);
    }

    private record TransactionHandler(FluxSink<TransactionReply> sink)
        implements StreamObserver<TransactionReply> {

        @Override
        public void onNext(TransactionReply value) {
            sink.next(value);
        }

        @Override
        public void onError(Throwable t) {
            sink.error(t);
        }

        @Override
        public void onCompleted() {
            sink.complete();
        }
    }
}
