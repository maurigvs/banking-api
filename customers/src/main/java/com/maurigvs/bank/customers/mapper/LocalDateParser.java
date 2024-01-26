package com.maurigvs.bank.customers.mapper;

import com.maurigvs.bank.customers.exception.BusinessException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

public class LocalDateParser implements Function<String, LocalDate> {

    private static final String FORMAT_ACCEPTED = "dd/MM/yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT_ACCEPTED);

    @Override
    public LocalDate apply(String date) {
        try {
            return LocalDate.from(FORMATTER.parse(date));

        } catch (DateTimeParseException exception){
            throw new BusinessException("date must be in the format: " + FORMAT_ACCEPTED);
        }
    }

    public String reverse(LocalDate date){
        return date.format(FORMATTER);
    }
}
