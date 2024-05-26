package br.maurigvs.banking.customer.grpc;

import br.maurigvs.banking.customer.exception.GrpcExceptionHandler;
import br.maurigvs.banking.customer.mapper.CustomerMapper;
import br.maurigvs.banking.customer.service.CustomerService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CustomerService service;

    @Override
    public void create(CustomerRequest request, StreamObserver<CustomerReply> response) {

        service.create(CustomerMapper.toEntity(request))
                .map(CustomerMapper::toReply)
                .doOnNext(r -> log.info("Customer created: {}", r))
                .doOnError(t -> log.warn("Error when creating customer: {}", t.getMessage()))
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }
}
