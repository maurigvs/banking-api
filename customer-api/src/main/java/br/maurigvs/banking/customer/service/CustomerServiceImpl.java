package br.maurigvs.banking.customer.service;

import br.maurigvs.banking.customer.exception.AlreadyExistsException;
import br.maurigvs.banking.customer.model.Customer;
import br.maurigvs.banking.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Mono<Customer> create(Customer customer) {
        return checkExistsByTaxId(customer)
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Customer> checkExistsByTaxId(Customer customer){
        return Mono.fromSupplier(() -> repository.existsByTaxId(customer.getTaxId()))
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(new AlreadyExistsException()))
                .thenReturn(customer)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
