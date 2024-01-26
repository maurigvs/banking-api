package com.maurigvs.bank.customers.mapper;

import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.mapper.LocalDateParser;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocalDateParserTest {

    @Test
    void should_return_LocalDate_given_an_String() {
        assertEquals(LocalDate.of(2024,1,1),
                new LocalDateParser().apply("01/01/2024"));
    }

    @Test
    void should_return_String_given_an_LocalDate() {
        assertEquals("31/12/2024",
                new LocalDateParser().reverse(LocalDate.of(2024,12,31)));
    }

    @Test
    void should_throw_Exception_given_Date_format_not_expected() {
        assertEquals("date must be in the format: dd/MM/yyyy",
                assertThrows(BusinessException.class,
                        () -> new LocalDateParser().apply("2024-01-01")).getLocalizedMessage());
    }
}