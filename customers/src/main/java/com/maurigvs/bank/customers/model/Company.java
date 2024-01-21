package com.maurigvs.bank.customers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "customer_id")
@Getter
public class Company extends Customer implements Serializable {

    private String businessName;
    private String legalName;
    private LocalDate startDate;

    public Company(Long id, String taxId, LocalDate since, Boolean enabled, ContactInfo contactInfo,
                   String businessName, String legalName, LocalDate startDate) {

        super(id, taxId, since, enabled, contactInfo);
        this.businessName = businessName;
        this.legalName = legalName;
        this.startDate = startDate;
    }
}