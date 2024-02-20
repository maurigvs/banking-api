package com.maurigvs.bank.customerapi.service;

import com.maurigvs.bank.customerapi.model.Person;
import com.maurigvs.bank.customerapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
class PersonServiceImpl implements PersonService {

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
