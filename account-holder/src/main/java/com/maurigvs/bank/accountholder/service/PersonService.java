package com.maurigvs.bank.accountholder.service;

import com.maurigvs.bank.accountholder.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.accountholder.exception.BusinessRuleException;
import com.maurigvs.bank.accountholder.model.Person;
import com.maurigvs.bank.accountholder.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonService extends AccountHolderService {

    private final PersonRepository personRepository;

    public void createPerson(CreatePersonRequest request) throws Exception {

        if(personAlreadyExists(request))
            throw new BusinessRuleException("The account holder already exists");

        personRepository.save(new Person(null, LocalDate.now(), true,
                request.name(), request.surname(), localDateFrom(request.birthDate()),
                request.taxIdNumber(), request.email(), request.phoneNumber()));
    }

    private boolean personAlreadyExists(CreatePersonRequest request) {
        return personRepository.existsByTaxIdNumber(request.taxIdNumber());
    }
}