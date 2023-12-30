package com.maurigvs.bank.accountholder.service;

import com.maurigvs.bank.accountholder.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.accountholder.exception.InvalidInputException;
import com.maurigvs.bank.accountholder.model.Person;
import com.maurigvs.bank.accountholder.repository.AccountHolderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountHolderService {

    private final AccountHolderRepository repository;

    public void createPerson(CreatePersonRequest request) throws Exception {

        repository.save(new Person(null, LocalDate.now(), true,
                request.name(), request.surname(), localDateFrom(request.birthDate()),
                request.taxIdNumber(), request.email(), request.phoneNumber()));
    }

    private static LocalDate localDateFrom(String localDateStr) throws InvalidInputException {

        final var dateFormat = "dd/MM/yyyy";
        try{
            return LocalDate.from(DateTimeFormatter.ofPattern(dateFormat).parse(localDateStr));

        } catch (DateTimeParseException exception){
            throw new InvalidInputException("The date must have the format: " + dateFormat);
        }
    }
}