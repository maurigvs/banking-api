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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {PersonServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    void should_create_person() {
        var person = new Person(null,
                "John",
                "Snow",
                "63592564528",
                LocalDate.of(1988,7,28),
                new ContactInfo("john.snow@gmail.com", "+351654358130"),
                LocalDate.now());

        personService.create(person);

        verify(personRepository, times(1)).save(person);
        verifyNoMoreInteractions(personRepository);
    }
}
