package com.maurigvs.bank.accountholder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "person_id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Person extends AccountHolder {

    private String name;

    private String surname;

    private LocalDate birthDate;

    @Column(unique = true)
    private String taxIdNumber;

    private String email;

    private String phoneNumber;

    public Person(Long id, LocalDate customerSince, boolean enabled, String name, String surname, LocalDate birthDate, String taxIdNumber, String email, String phoneNumber) {
        super(id, customerSince, enabled);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.taxIdNumber = taxIdNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}