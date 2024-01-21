package com.maurigvs.bank.customers.repository;

import com.maurigvs.bank.customers.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByCpf(String cpf);
}