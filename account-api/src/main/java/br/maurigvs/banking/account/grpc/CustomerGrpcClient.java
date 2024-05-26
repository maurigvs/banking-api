package br.maurigvs.banking.account.grpc;

import br.maurigvs.banking.account.exception.GrpcExceptionHandler;
import br.maurigvs.banking.customer.grpc.CustomerReply;
import br.maurigvs.banking.customer.grpc.CustomerRequest;
import br.maurigvs.banking.customer.grpc.CustomerServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerGrpcClient {

    private final CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceBlockingStub;

    public Mono<Long> create(CustomerRequest request){
        return Mono.fromSupplier(() -> customerServiceBlockingStub.create(request))
                .map(CustomerReply::getId)
                .doOnNext(r -> log.info("Customer created with id: {}", r))
                .doOnError(t -> log.warn("Error creating Customer: {}", t.getMessage()))
                .onErrorMap(GrpcExceptionHandler::toClientException);
    }
}
