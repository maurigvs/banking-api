package br.com.maurigvs.customer.service;

import br.com.maurigvs.customer.model.Person;
import br.com.maurigvs.customer.repository.PersonRepository;
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
