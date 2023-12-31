package com.maurigvs.bank.accountholder.service;

import com.maurigvs.bank.accountholder.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.accountholder.exception.BusinessRuleException;
import com.maurigvs.bank.accountholder.model.Person;
import com.maurigvs.bank.accountholder.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonRepository repository;

    public void createPerson(CreatePersonRequest request) throws Exception {

        if(personAlreadyExists(request))
            throw new BusinessRuleException("The account holder already exists");

        repository.save(new Person(null, LocalDate.now(), true,
                request.name(), request.surname(), localDateFrom(request.birthDate()),
                request.taxIdNumber(), request.email(), request.phoneNumber()));
    }

    private boolean personAlreadyExists(CreatePersonRequest request) {
        return repository.existsByTaxIdNumber(request.taxIdNumber());
    }

    private static LocalDate localDateFrom(String localDateStr) throws BusinessRuleException {

        final var dateFormat = "dd/MM/yyyy";
        try{
            return LocalDate.from(DateTimeFormatter.ofPattern(dateFormat).parse(localDateStr));

        } catch (DateTimeParseException exception){
            throw new BusinessRuleException("The date must have the format: " + dateFormat);
        }
    }
}