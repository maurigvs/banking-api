package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Customer;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RequiredArgsConstructor
abstract class CustomerService {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private final CustomerRepository customerRepository;

    void createCustomer(Customer customer){
        customerRepository.save(customer);
    }

    boolean existsByTaxId(String taxId){
        return customerRepository.existsByTaxId(taxId);
    }

    LocalDate parseLocalDate(String localDate) throws BusinessException {
        try{
            return LocalDate.from(DateTimeFormatter.ofPattern(DATE_FORMAT).parse(localDate));

        } catch (DateTimeParseException exception){
            throw new BusinessException("date must have the format: " + DATE_FORMAT);
        }
    }
}