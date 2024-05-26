package br.maurigvs.banking.account.config;

import br.maurigvs.banking.customer.grpc.CustomerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GrpcClient {

    @Bean
    public ManagedChannel managedChannel(@Value("${grpc.clients.customer.host}") String host, @Value("${grpc.clients.customer.port}") Integer port) {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }

    @Bean
    public CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceBlockingStub(ManagedChannel managedChannel) {
        return CustomerServiceGrpc.newBlockingStub(managedChannel);
    }
}
