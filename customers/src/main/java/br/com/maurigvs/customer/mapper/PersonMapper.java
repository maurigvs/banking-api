package br.com.maurigvs.customer.mapper;

import br.com.maurigvs.customer.dto.PersonRequest;
import br.com.maurigvs.customer.model.Person;

import java.util.function.Function;

public class PersonMapper implements Function<PersonRequest, Person> {

    @Override
    public Person apply(PersonRequest request) {
        var birthDate = new LocalDateParser().apply(request.birthDate());

        return new Person(null,
                request.name(),
                request.surname(),
                request.cpf(),
                birthDate,
                request.emailAddress(),
                request.phoneNumber());
    }
}
