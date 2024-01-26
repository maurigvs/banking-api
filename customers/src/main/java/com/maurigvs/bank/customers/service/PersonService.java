package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Person;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonService implements CustomerService<Person> {

    private final CustomerRepository repository;

    @Override
    public void create(Person person) throws BusinessException {
        validateBirthDate(person.getBirthDate());
        validateAlreadyExists(person.getTaxId());
        repository.save(person);
    }

    private void validateBirthDate(LocalDate birthDate) throws BusinessException {
        if(birthDate.isAfter(LocalDate.now().minusYears(18)))
            throw new BusinessException("The account holder must have 18 years of age completed.");
    }

    private void validateAlreadyExists(String taxId) throws BusinessException {
        if(repository.existsByTaxId(taxId))
            throw new BusinessException("Customer already exists");
    }
}