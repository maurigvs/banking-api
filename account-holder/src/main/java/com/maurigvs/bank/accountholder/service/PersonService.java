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

        var birthDate = localDateFrom(request.birthDate());

        if(personIsUnderAge(birthDate))
            throw new BusinessRuleException("The account holder must have 18 years of age completed.");

        if(personAlreadyExists(request))
            throw new BusinessRuleException("The account holder already exists");

        personRepository.save(new Person(null, LocalDate.now(), true,
                request.name(), request.surname(), birthDate,
                request.cpf(), request.email(), request.phoneNumber()));
    }

    private boolean personIsUnderAge(LocalDate birthDate) {
        return birthDate.isAfter(LocalDate.now().minusYears(18));
    }

    private boolean personAlreadyExists(CreatePersonRequest request) {
        return personRepository.existsByCpf(request.cpf());
    }
}