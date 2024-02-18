package br.com.maurigvs.customer.service;

import br.com.maurigvs.customer.model.Person;
import br.com.maurigvs.customer.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void create(Person person) {
        personRepository.save(person);
    }
}
