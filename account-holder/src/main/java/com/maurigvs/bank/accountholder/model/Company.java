package com.maurigvs.bank.accountholder.model;

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
public class Company extends AccountHolder {

    private String legalName;

    private String businessName;

    private LocalDate openingDate;

    @Column(unique = true)
    private String taxIdNumber;

    private String email;

    private String phoneNumber;

    public Company(Long id, LocalDate customerSince, boolean enabled, String legalName, String businessName, LocalDate openingDate, String taxIdNumber, String email, String phoneNumber) {
        super(id, customerSince, enabled);
        this.legalName = legalName;
        this.businessName = businessName;
        this.openingDate = openingDate;
        this.taxIdNumber = taxIdNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}