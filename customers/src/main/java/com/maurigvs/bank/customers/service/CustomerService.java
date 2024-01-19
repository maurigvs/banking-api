package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.model.dto.CompanyRequest;
import com.maurigvs.bank.customers.model.dto.PersonRequest;
import com.maurigvs.bank.customers.model.entity.Company;
import com.maurigvs.bank.customers.model.entity.ContactInfo;
import com.maurigvs.bank.customers.model.entity.Customer;
import com.maurigvs.bank.customers.model.entity.Person;
import com.maurigvs.bank.customers.model.exception.BusinessException;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final int AGE_ELEGIBLE = 18;

    public void createPerson(PersonRequest eq) throws BusinessException {
        var birthDate = parseLocalDate(eq.birthDate());

        if(isBirthDateNotElegible(birthDate))
            throw new BusinessException("Person has age not elegible");

        var person = new Person(null, eq.taxId(), LocalDate.now(), true,
                new ContactInfo(eq.email(), eq.phone()),
                eq.name(), eq.surname(), birthDate);

        saveCustomer(person);
    }

    private boolean isBirthDateNotElegible(LocalDate birthDate) {
        return LocalDate.now().minusYears(AGE_ELEGIBLE).isBefore(birthDate);
    }

    public void createCompany(CompanyRequest eq) {
        var startDate = parseLocalDate(eq.startDate());

        var company = new Company(null, eq.taxId(), LocalDate.now(), true,
                new ContactInfo(eq.contactEmail(), eq.contactPhone()),
                eq.businessName(), eq.legalName(), startDate);

        saveCustomer(company);
    }

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }

    private LocalDate parseLocalDate(String dateString) {
        try{
            return LocalDate.from(DateTimeFormatter.ofPattern(DATE_FORMAT).parse(dateString));
        } catch (DateTimeParseException exception){
            throw new IllegalArgumentException("birthDate must be in the format " + DATE_FORMAT);
        }
    }
}
