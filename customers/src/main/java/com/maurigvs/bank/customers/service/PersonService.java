package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.controller.dto.PersonRequest;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.ContactInfo;
import com.maurigvs.bank.customers.model.Person;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonService extends CustomerService {

    public PersonService(CustomerRepository customerRepository) {
        super(customerRepository);
    }

    public void createPerson(PersonRequest request) throws BusinessException {

        var birthDate = parseLocalDate(request.birthDate());

        if(isAgeLessThanEighteenYears(birthDate))
            throw new BusinessException("The account holder must have 18 years of age completed.");

        if(existsByTaxId(request.taxId()))
            throw new BusinessException("Customer already exists");

        createCustomer(
            new Person(null, request.taxId(), LocalDate.now(), true,
                    new ContactInfo(request.email(), request.phoneNumber()),
                    request.name(), request.surname(), birthDate)
        );
    }

    private boolean isAgeLessThanEighteenYears(LocalDate localDate) {
        return localDate.isAfter(LocalDate.now().minusYears(18));
    }
}