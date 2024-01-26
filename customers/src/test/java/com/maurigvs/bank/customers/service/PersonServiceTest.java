package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.ContactInfo;
import com.maurigvs.bank.customers.model.Person;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(classes = {PersonService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonServiceTest {

    @Autowired
    private PersonService service;

    @MockBean
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        given(repository.existsByTaxId(anyString())).willReturn(false);
    }

    @Test
    void should_create_person_successfully() throws Exception {

        var person = new Person(null,"86580512180", LocalDate.now(), true,
                new ContactInfo("john@wayne.com", "+5511984833929"),
                "John", "Wayne", LocalDate.of(1988,7,28));

        service.create(person);

        then(repository).should().existsByTaxId(person.getTaxId());
        then(repository).should().save(eq(person));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_BusinessException_when_person_has_less_than_18_years_of_age() {

        var birthDate = LocalDate.now().minusYears(18).plusDays(1);

        var person = new Person(null,"86580512180", LocalDate.now(), true,
                new ContactInfo("john@wayne.com", "+5511984833929"),
                "John", "Wayne", birthDate);

        assertEquals("The account holder must have 18 years of age completed.",
                assertThrows(BusinessException.class, () ->
                        service.create(person)).getLocalizedMessage());

        then(repository).shouldHaveNoInteractions();
    }

    @Test
    void should_throw_BusinessException_when_person_already_exists() {

        var person = new Person(null,"86580512180", LocalDate.now(), true,
                new ContactInfo("john@wayne.com", "+5511984833929"),
                "John", "Wayne", LocalDate.of(1988,7,28));

        given(repository.existsByTaxId(anyString())).willReturn(true);

        assertEquals("Customer already exists",
                assertThrows(BusinessException.class, () ->
                        service.create(person)).getLocalizedMessage());

        then(repository).should().existsByTaxId(person.getTaxId());
        then(repository).shouldHaveNoMoreInteractions();
    }
}