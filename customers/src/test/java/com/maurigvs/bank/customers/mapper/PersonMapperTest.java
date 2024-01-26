package com.maurigvs.bank.customers.mapper;

import com.maurigvs.bank.customers.dto.PersonRequest;
import com.maurigvs.bank.customers.mapper.PersonMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonMapperTest {

    @Test
    void should_return_Person_given_an_PersonRequest() {

        var request = new PersonRequest("86580512180", "John", "Wayne",
                "28/07/1988", "john@wayne.com", "+5511984833929");

        var since = LocalDate.now();
        var birthDate = LocalDate.of(1988,7,28);

        var result = new PersonMapper().apply(request);

        assertNull(result.getId());
        assertEquals(request.taxId(), result.getTaxId());
        assertEquals(since, result.getSince());
        assertTrue(result.getEnabled());
        assertEquals(request.name(), result.getName());
        assertEquals(request.surname(), result.getSurname());
        assertEquals(birthDate, result.getBirthDate());
        assertEquals(request.email(), result.getContactInfo().getEmail());
        assertEquals(request.phoneNumber(), result.getContactInfo().getPhone());
    }
}