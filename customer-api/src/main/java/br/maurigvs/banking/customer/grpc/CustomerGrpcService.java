package br.maurigvs.banking.customer.grpc;

import br.maurigvs.banking.customer.exception.GrpcExceptionHandler;
import br.maurigvs.banking.customer.model.mapper.CustomerMapper;
import br.maurigvs.banking.customer.service.CustomerService;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CustomerService customerService;

    @Override
    public void create(CustomerRequest request, StreamObserver<CustomerReply> response) {

        customerService.create(CustomerMapper.toEntity(request))
                .map(CustomerMapper::toReply)
                .doOnError(t -> log.error("Error when creating Customer: {}", t.getMessage()))
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }

    @Override
    public void findById(Int64Value request, StreamObserver<CustomerReply> response) {

        customerService.findById(request.getValue())
                .map(CustomerMapper::toReply)
                .doOnError(t -> log.error("Error when finding Customer by Id {}: {}", request.getValue(), t.getMessage()))
                .onErrorMap(GrpcExceptionHandler::toServerException)
                .subscribe(response::onNext, response::onError, response::onCompleted);
    }
}
