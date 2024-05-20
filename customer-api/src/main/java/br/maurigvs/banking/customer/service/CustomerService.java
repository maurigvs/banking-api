package br.maurigvs.banking.customer.service;

import br.maurigvs.banking.customer.model.entity.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> create(Customer customer);

    Flux<Customer> findAll();
}
