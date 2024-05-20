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

    @Value("${grpc.clients.customer.host}")
    private String host;

    @Value("${grpc.clients.customer.port}")
    private Integer port;

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }

    @Bean
    public CustomerServiceGrpc.CustomerServiceStub customerServiceStub(ManagedChannel managedChannel) {
        return CustomerServiceGrpc.newStub(managedChannel);
    }

    @Bean
    public CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceBlockingStub(ManagedChannel managedChannel) {
        return CustomerServiceGrpc.newBlockingStub(managedChannel);
    }
}
