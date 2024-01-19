package com.maurigvs.bank.customers.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "person_id")
@Getter
@EqualsAndHashCode(callSuper = true)
public class Person extends Customer implements Serializable {

    private String name;
    private String surname;
    private LocalDate birthDate;

    public Person(Long id, String taxId, LocalDate since, Boolean active, ContactInfo contactInfo,
                  String name, String surname, LocalDate birthDate) {

        super(id, taxId, since, active, contactInfo);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }
}
