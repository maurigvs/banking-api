package com.maurigvs.bank.customerapi.mapper;

import com.maurigvs.bank.customerapi.exception.DateFormatException;
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
        var expected = LocalDate.of(2024,1,1);

        var result = new LocalDateParser().apply("01/01/2024");

        assertEquals(expected, result);
    }

    @Test
    void should_throw_Exception_given_an_date_with_wrong_format() {
        var mapper = new LocalDateParser();
        var expected = "Date must be in the format: dd/MM/yyyy";

        var exception = assertThrows(DateFormatException.class,
                () -> mapper.apply("01-01-24"));

        assertEquals(expected, exception.getMessage());
    }
}
