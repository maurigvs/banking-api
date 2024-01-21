package com.maurigvs.bank.customers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "customer_id")
@Getter
public class Person extends Customer implements Serializable {

    private String name;
    private String surname;
    private LocalDate birthDate;

    public Person(Long id, String taxId, LocalDate since, Boolean enabled, ContactInfo contactInfo,
                  String name, String surname, LocalDate birthDate) {

        super(id, taxId, since, enabled, contactInfo);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }
}