package br.com.maurigvs.customer.service;

import br.com.maurigvs.customer.model.ContactInfo;
import br.com.maurigvs.customer.model.Person;
import br.com.maurigvs.customer.repository.PersonRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {PersonServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonServiceImplTest {

    @Autowired
    private PersonService service;

    @MockBean
    private PersonRepository repository;

    @Test
    void should_create_person() {
        var person = new Person(null,
                "John",
                "Snow",
                "63592564528",
                LocalDate.of(1988,7,28),
                new ContactInfo("john.snow@gmail.com", "+351654358130"),
                LocalDate.now());

        service.create(person);

        verify(repository, times(1)).save(person);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_Person_when_find_by_taxId() {
        var person = new Person(1L,
                "John",
                "Snow",
                "63592564528",
                LocalDate.of(1988,7,28),
                new ContactInfo("john.snow@gmail.com", "+351654358130"),
                LocalDate.now());
        var cpf = "63592564528";
        given(repository.findByCpf(anyString())).willReturn(Optional.of(person));

        var result = service.findByTaxId(cpf);

        verify(repository, times(1)).findByCpf(cpf);
        verifyNoMoreInteractions(repository);
        assertSame(person, result);
    }

    @Test
    void should_throw_Exception_when_person_not_found_by_taxId() {
        var cpf = "63592564528";
        var expected = "No value present";
        given(repository.findByCpf(anyString())).willReturn(Optional.empty());

        var result = assertThrows(NoSuchElementException.class,
                () -> service.findByTaxId(cpf));

        verify(repository, times(1)).findByCpf(cpf);
        verifyNoMoreInteractions(repository);
        assertSame(expected, result.getMessage());
    }
}
