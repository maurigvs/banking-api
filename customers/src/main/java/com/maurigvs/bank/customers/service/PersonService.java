package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.controller.dto.PostPersonDto;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Person;
import com.maurigvs.bank.customers.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonService extends CustomerService {

    private final PersonRepository personRepository;

    public void createPerson(PostPersonDto request) throws Exception {

        var birthDate = localDateFrom(request.birthDate());

        if(personIsUnderAge(birthDate))
            throw new BusinessException("The account holder must have 18 years of age completed.");

        if(personAlreadyExists(request))
            throw new BusinessException("The account holder already exists");

        personRepository.save(new Person(null, LocalDate.now(), true,
                request.name(), request.surname(), birthDate,
                request.taxId(), request.email(), request.phoneNumber()));
    }

    private boolean personIsUnderAge(LocalDate birthDate) {
        return birthDate.isAfter(LocalDate.now().minusYears(18));
    }

    private boolean personAlreadyExists(PostPersonDto request) {
        return personRepository.existsByCpf(request.taxId());
    }
}