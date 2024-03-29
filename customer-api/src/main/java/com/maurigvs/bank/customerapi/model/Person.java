package com.maurigvs.bank.customerapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "customer_id")
public class Person extends Customer implements Serializable {

    private String name;
    private String surname;
    private String cpf;
    private LocalDate birthDate;

    public Person(Long id, String name, String surname, String cpf, LocalDate birthDate, ContactInfo contactInfo, LocalDate createdAt) {
        super(id, contactInfo, createdAt);
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    protected Person() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
