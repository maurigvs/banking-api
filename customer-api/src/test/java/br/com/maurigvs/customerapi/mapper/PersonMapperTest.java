package br.com.maurigvs.customerapi.mapper;

import br.com.maurigvs.customerapi.dto.PersonRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonMapperTest {

    @Test
    void should_return_Person_given_an_PersonRequest() {
        var request = new PersonRequest(
                "John",
                "Snow",
                "63592564528",
                "28/07/1988",
                "john.snow@gmail.com",
                "+351654358130");

        var birthDate = LocalDate.of(1988,7,28);
        var createdAt = LocalDate.now();

        var result = new PersonMapper().apply(request);

        assertNull(result.getId());
        assertEquals(request.name(), result.getName());
        assertEquals(request.surname(), result.getSurname());
        assertEquals(request.cpf(), result.getCpf());
        assertEquals(birthDate, result.getBirthDate());
        assertEquals(request.emailAddress(), result.getContactInfo().getEmailAddress());
        assertEquals(request.phoneNumber(), result.getContactInfo().getPhoneNumber());
        assertEquals(createdAt, result.getCreatedAt());
    }
}
