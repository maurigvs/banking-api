package br.maurigvs.banking.customer.service;

import br.maurigvs.banking.customer.exception.AlreadyExistsException;
import br.maurigvs.banking.customer.model.Customer;
import br.maurigvs.banking.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {CustomerServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerServiceImplTest {

    @Autowired
    private CustomerService service;

    @MockBean
    private CustomerRepository repository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(null,
                "12345678901",
                "John",
                "Doe",
                "john.doe@gmail.com",
                "987654321",
                LocalDate.of(1988,7,28),
                LocalDate.now());
    }

    @Test
    void should_return_Customer_created_when_create_new_Customer() {
        given(repository.existsByTaxId(anyString())).willReturn(false);
        given(repository.save(customer)).willReturn(customer);

        StepVerifier.create(service.create(customer))
                .expectNext(customer)
                .verifyComplete();

        then(repository).should(times(1)).existsByTaxId(customer.getTaxId());
        then(repository).should(times(1)).save(customer);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_AlreadyExistsException_when_Customer_already_exists_by_TaxId() {
        given(repository.existsByTaxId(anyString())).willReturn(true);

        StepVerifier.create(service.create(customer))
                .expectErrorMatches(throwable -> throwable instanceof AlreadyExistsException &&
                        throwable.getMessage().equals("Customer already exists"))
                .verify();

        then(repository).should(times(1)).existsByTaxId(customer.getTaxId());
        then(repository).shouldHaveNoMoreInteractions();
    }
}
