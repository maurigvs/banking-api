package br.com.maurigvs.customerapi.mapper;

import br.com.maurigvs.customerapi.dto.PersonRequest;
import br.com.maurigvs.customerapi.model.ContactInfo;
import br.com.maurigvs.customerapi.model.Person;

import java.time.LocalDate;
import java.util.function.Function;

public class PersonMapper implements Function<PersonRequest, Person> {

    @Override
    public Person apply(PersonRequest request) {
        var birthDate = new LocalDateParser().apply(request.birthDate());
        var contactInfo = new ContactInfo(request.emailAddress(), request.phoneNumber());
        var createdAt = LocalDate.now();

        return new Person(null,
                request.name(),
                request.surname(),
                request.cpf(),
                birthDate,
                contactInfo,
                createdAt);
    }
}
