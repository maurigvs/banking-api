package br.maurigvs.banking.transaction.config;

import br.maurigvs.banking.account.grpc.AccountServiceGrpc;
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
    public ManagedChannel managedChannel(@Value("${grpc.clients.account.host}") String host,
                                         @Value("${grpc.clients.account.port}") Integer port) {
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
    }

    @Bean
    public AccountServiceGrpc.AccountServiceBlockingStub accountServiceBlockingStub(ManagedChannel managedChannel) {
        return AccountServiceGrpc.newBlockingStub(managedChannel);
    }

    @Bean
    public AccountServiceGrpc.AccountServiceStub accountServiceStub(ManagedChannel managedChannel) {
        return AccountServiceGrpc.newStub(managedChannel);
    }
}
