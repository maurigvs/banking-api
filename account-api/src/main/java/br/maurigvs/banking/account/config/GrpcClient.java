package br.maurigvs.banking.account.config;

import br.maurigvs.banking.customer.grpc.CustomerServiceGrpc;
import br.maurigvs.banking.transaction.grpc.TransactionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GrpcClient {

    @Bean
    public ManagedChannel managedChannel(@Value("${grpc.clients.customer.host}") String host, @Value("${grpc.clients.customer.port}") Integer port) {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }

    @Bean
    @Qualifier("secondManagedChannel")
    public ManagedChannel secondManagedChannel(@Value("${grpc.clients.transaction.host}") String host, @Value("${grpc.clients.transaction.port}") Integer port) {
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

    @Bean
    public TransactionServiceGrpc.TransactionServiceBlockingStub transactionServiceBlockingStub(@Qualifier("secondManagedChannel") ManagedChannel secondManagedChannel) {
        return TransactionServiceGrpc.newBlockingStub(secondManagedChannel);
    }

    @Bean
    public TransactionServiceGrpc.TransactionServiceStub transactionServiceStub(@Qualifier("secondManagedChannel") ManagedChannel secondManagedChannel) {
        return TransactionServiceGrpc.newStub(secondManagedChannel);
    }
}
