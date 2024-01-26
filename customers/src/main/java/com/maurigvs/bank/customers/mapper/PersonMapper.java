package com.maurigvs.bank.customers.mapper;

import com.maurigvs.bank.customers.dto.PersonRequest;
import com.maurigvs.bank.customers.model.ContactInfo;
import com.maurigvs.bank.customers.model.Person;

import java.util.function.Function;

public class PersonMapper extends CustomerMapper implements Function<PersonRequest, Person> {

    @Override
    public Person apply(PersonRequest request) {

        var contactInfo = new ContactInfo(request.email(), request.phoneNumber());
        var birthDate = new LocalDateParser().apply(request.birthDate());

        return new Person(null, request.taxId(), DEFAULT_SINCE, DEFAULT_ENABLED,
                contactInfo, request.name(), request.surname(), birthDate);
    }
}
