package br.maurigvs.banking.customer.service;

import br.maurigvs.banking.customer.exception.AlreadyExistsException;
import br.maurigvs.banking.customer.model.entity.Customer;
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
        return checkIfExists(customer)
                .map(customerRepository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Customer> findAll() {
        return Flux.fromStream(customerRepository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Customer> checkIfExists(Customer customer){
        return Mono.fromSupplier(() -> customerRepository.existsByTaxId(customer.getTaxId()))
                .filter(result -> result.equals(false))
                .switchIfEmpty(Mono.error(new AlreadyExistsException()))
                .thenReturn(customer)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
