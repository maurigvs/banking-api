package br.maurigvs.banking.customer.service;

import br.maurigvs.banking.customer.model.Customer;
import br.maurigvs.banking.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<Customer> create(Customer customer) {
        return Mono.fromSupplier(() -> customerRepository.save(customer))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Customer> findAll() {
        return Flux.fromStream(customerRepository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

}
