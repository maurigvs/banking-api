package com.maurigvs.bank.customers.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "company_id")
@Getter
@EqualsAndHashCode(callSuper = true)
public class Company extends Customer implements Serializable {

    private String businessName;
    private String legalName;
    private LocalDate startDate;

    public Company(Long id, String taxId, LocalDate since, Boolean active, ContactInfo contactInfo,
                   String businessName, String legalName, LocalDate startDate) {

        super(id, taxId, since, active, contactInfo);
        this.businessName = businessName;
        this.legalName = legalName;
        this.startDate = startDate;
    }
}
