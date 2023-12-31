package com.maurigvs.bank.accountholder.service;

import com.maurigvs.bank.accountholder.Mocks;
import com.maurigvs.bank.accountholder.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.accountholder.exception.BusinessRuleException;
import com.maurigvs.bank.accountholder.model.Person;
import com.maurigvs.bank.accountholder.repository.PersonRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {PersonService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonServiceTest {

    @Autowired
    PersonService personService;

    @MockBean
    PersonRepository personRepository;

    @Captor
    ArgumentCaptor<Person> personCaptor;

    @BeforeEach
    void setUp() {
        given(personRepository.existsByTaxIdNumber(anyString())).willReturn(false);
    }

    @Test
    void should_create_person_successfully() throws Exception {

        var request = Mocks.createPersonRequest();
        given(personRepository.save(any(Person.class))).willReturn(Mocks.person());

        personService.createPerson(request);

        then(personRepository).should(times(1)).existsByTaxIdNumber(request.taxIdNumber());
        then(personRepository).should(times(1)).save(personCaptor.capture());
        then(personRepository).shouldHaveNoMoreInteractions();

        var person = personCaptor.getValue();
        assertThat(person.getId()).isNull();
        assertThat(person.getCustomerSince()).isEqualTo(LocalDate.now());
        assertThat(person.isEnabled()).isTrue();
        assertThat(person.getName()).isEqualTo(request.name());
        assertThat(person.getSurname()).isEqualTo(request.surname());
        assertThat(person.getBirthDate()).isEqualTo(LocalDate.of(1988,7,28));
        assertThat(person.getTaxIdNumber()).isEqualTo(request.taxIdNumber());
        assertThat(person.getEmail()).isEqualTo(request.email());
        assertThat(person.getPhoneNumber()).isEqualTo(request.phoneNumber());
    }

    @Test
    void should_throw_exception_if_person_already_exists() {

        var request = Mocks.createPersonRequest();
        given(personRepository.existsByTaxIdNumber(anyString())).willReturn(true);

        assertThatExceptionOfType(BusinessRuleException.class)
                .isThrownBy(() -> personService.createPerson(request))
                .withMessage("The account holder already exists");

        then(personRepository).should(times(1)).existsByTaxIdNumber(request.taxIdNumber());
        then(personRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_exception_if_birth_date_is_invalid_when_create_person() {

        var request = new CreatePersonRequest("John",
                "Wayne",
                "1988-07-28",
                "86580512180",
                "john@wayne.com",
                "+5511984833929");

        assertThatExceptionOfType(BusinessRuleException.class)
                .isThrownBy(() -> personService.createPerson(request))
                .withMessage("The date must have the format: dd/MM/yyyy");
    }
}