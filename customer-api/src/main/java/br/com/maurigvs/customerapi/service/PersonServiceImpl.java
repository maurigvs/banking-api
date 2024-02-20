package br.com.maurigvs.customerapi.service;

import br.com.maurigvs.customerapi.model.Person;
import br.com.maurigvs.customerapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Person person) {
        repository.save(person);
    }

    @Override
    public Person findByTaxId(String taxId) {
        return repository.findByCpf(taxId).orElseThrow();
    }
}
