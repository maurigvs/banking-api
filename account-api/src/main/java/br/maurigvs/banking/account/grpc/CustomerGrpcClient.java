package br.maurigvs.banking.account.grpc;

import br.maurigvs.banking.account.exception.GrpcExceptionHandler;
import br.maurigvs.banking.customer.grpc.CustomerReply;
import br.maurigvs.banking.customer.grpc.CustomerRequest;
import br.maurigvs.banking.customer.grpc.CustomerServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerGrpcClient {

    private final CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceBlockingStub;

    public Mono<Long> create(CustomerRequest request){
        return Mono.fromSupplier(() -> customerServiceBlockingStub.create(request))
                .map(CustomerReply::getId)
                .onErrorMap(GrpcExceptionHandler::toClientException);
    }
}
