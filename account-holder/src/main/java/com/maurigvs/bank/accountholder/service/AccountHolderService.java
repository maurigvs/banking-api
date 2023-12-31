package com.maurigvs.bank.accountholder.service;

import com.maurigvs.bank.accountholder.exception.BusinessRuleException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class AccountHolderService {

    protected static LocalDate localDateFrom(String localDateStr) throws BusinessRuleException {

        final var dateFormat = "dd/MM/yyyy";
        try{
            return LocalDate.from(DateTimeFormatter.ofPattern(dateFormat).parse(localDateStr));

        } catch (DateTimeParseException exception){
            throw new BusinessRuleException("The date must have the format: " + dateFormat);
        }
    }
}