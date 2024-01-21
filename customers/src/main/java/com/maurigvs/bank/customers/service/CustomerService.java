package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.exception.BusinessRuleException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class CustomerService {

    protected static LocalDate localDateFrom(String localDateStr) throws BusinessRuleException {

        final var dateFormat = "dd/MM/yyyy";
        try{
            return LocalDate.from(DateTimeFormatter.ofPattern(dateFormat).parse(localDateStr));

        } catch (DateTimeParseException exception){
            throw new BusinessRuleException("The date must have the format: " + dateFormat);
        }
    }
}