package com.maurigvs.bank.customers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "company_id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company extends Customer {

    private String legalName;

    private String businessName;

    private LocalDate openingDate;

    @Column(unique = true)
    private String cnpj;

    private String email;

    private String phoneNumber;

    public Company(Long id, LocalDate customerSince, boolean enabled, String legalName, String businessName, LocalDate openingDate, String cnpj, String email, String phoneNumber) {
        super(id, customerSince, enabled);
        this.legalName = legalName;
        this.businessName = businessName;
        this.openingDate = openingDate;
        this.cnpj = cnpj;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}