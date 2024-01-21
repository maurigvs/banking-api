package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.exception.BusinessException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class CustomerService {

    protected static LocalDate localDateFrom(String localDateStr) throws BusinessException {

        final var dateFormat = "dd/MM/yyyy";
        try{
            return LocalDate.from(DateTimeFormatter.ofPattern(dateFormat).parse(localDateStr));

        } catch (DateTimeParseException exception){
            throw new BusinessException("The date must have the format: " + dateFormat);
        }
    }
}