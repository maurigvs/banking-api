package br.maurigvs.banking.customer.config;

import br.maurigvs.banking.customer.grpc.CustomerGrpcService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServer {

    @Value("${grpc.server.port}")
    private Integer port;

    @Bean
    public Server serverBuilder(CustomerGrpcService customerGrpcService) {
        return ServerBuilder.forPort(port)
                .directExecutor()
                .addService(customerGrpcService)
                .build();
    }
}
