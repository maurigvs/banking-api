package br.maurigvs.banking.customer.service;

import br.maurigvs.banking.customer.exception.AgeNotEligibleException;
import br.maurigvs.banking.customer.exception.AlreadyExistsException;
import br.maurigvs.banking.customer.exception.NotFoundException;
import br.maurigvs.banking.customer.model.entity.Customer;
import br.maurigvs.banking.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<Customer> create(Customer customer) {
        return checkIfExists(customer)
                .flatMap(this::checkEligibleAge)
                .flatMap(this::save);
    }

    @Override
    public Mono<Customer> findById(Long id) {
        return Mono.fromSupplier(() -> customerRepository.findById(id))
                .filter(Optional::isPresent)
                .switchIfEmpty(Mono.error(new NotFoundException(id)))
                .map(Optional::get)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Customer> findAll() {
        return Flux.fromStream(customerRepository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Customer> save(Customer customer) {
        return Mono.fromSupplier(() -> customerRepository.save(customer))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Customer> checkEligibleAge(Customer customer){
        return Mono.just(customer)
                .filter(c -> c.getBirthDate().isBefore(LocalDate.now().minusYears(18)))
                .switchIfEmpty(Mono.error(new AgeNotEligibleException()))
                .thenReturn(customer);
    }

    public Mono<Customer> checkIfExists(Customer customer){
        return Mono.fromSupplier(() -> customerRepository.existsByTaxId(customer.getTaxId()))
                .filter(result -> result.equals(false))
                .switchIfEmpty(Mono.error(new AlreadyExistsException()))
                .thenReturn(customer)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
