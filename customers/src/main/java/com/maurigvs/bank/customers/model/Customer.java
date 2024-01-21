package com.maurigvs.bank.customers.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@Getter
public abstract class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taxId;

    private LocalDate since;

    private Boolean enabled;

    @Embedded
    private ContactInfo contactInfo;
}