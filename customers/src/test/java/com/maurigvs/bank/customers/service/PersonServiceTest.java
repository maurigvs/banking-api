package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.controller.dto.PersonRequest;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Person;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(classes = {PersonService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonServiceTest {

    @Autowired
    PersonService personService;

    @MockBean
    CustomerRepository customerRepository;

    @Captor
    ArgumentCaptor<Person> personCaptor;

    @BeforeEach
    void setUp() {
        given(customerRepository.existsByTaxId(anyString())).willReturn(false);
    }

    @Test
    void should_create_person_successfully() throws Exception {

        var request = new PersonRequest("86580512180", "John", "Wayne",
                "28/07/1988", "john@wayne.com", "+5511984833929");

        personService.createPerson(request);

        then(customerRepository).should().existsByTaxId(request.taxId());
        then(customerRepository).should().save(personCaptor.capture());
        then(customerRepository).shouldHaveNoMoreInteractions();

        var person = personCaptor.getValue();
        assertNull(person.getId());
        assertEquals(request.taxId(), person.getTaxId());
        assertEquals(LocalDate.now(), person.getSince());
        assertTrue(person.getEnabled());
        assertEquals(request.name(), person.getName());
        assertEquals(request.surname(), person.getSurname());
        assertEquals(LocalDate.of(1988,7,28), person.getBirthDate());
        assertEquals(request.email(), person.getContactInfo().getEmail());
        assertEquals(request.phoneNumber(), person.getContactInfo().getPhone());
    }

    @Test
    void should_throw_BusinessException_when_person_has_less_than_18_years_of_age() {

        var birthDate = LocalDate.now()
                .minusYears(18)
                .plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        var request = new PersonRequest("86580512180", "John", "Wayne",
                birthDate, "john@wayne.com", "+5511984833929");

        assertEquals("The account holder must have 18 years of age completed.",
                assertThrows(BusinessException.class, () ->
                        personService.createPerson(request)).getLocalizedMessage());

        then(customerRepository).shouldHaveNoInteractions();
    }

    @Test
    void should_throw_BusinessException_when_person_already_exists() {

        var request = new PersonRequest("86580512180", "John", "Wayne",
                "28/07/1988", "john@wayne.com", "+5511984833929");

        given(customerRepository.existsByTaxId(anyString())).willReturn(true);

        assertEquals("Customer already exists",
                assertThrows(BusinessException.class, () ->
                        personService.createPerson(request)).getLocalizedMessage());

        then(customerRepository).should().existsByTaxId(request.taxId());
        then(customerRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_BusinessException_when_birthDate_has_invalid_format() {

        var request = new PersonRequest("86580512180", "John", "Wayne",
                "1988-07-28", "john@wayne.com", "+5511984833929");

        assertEquals("date must have the format: dd/MM/yyyy",
                assertThrows(BusinessException.class, () ->
                        personService.createPerson(request)).getLocalizedMessage());

        then(customerRepository).shouldHaveNoInteractions();
    }
}