package br.com.maurigvs.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessName;
    private String legalName;
    private String cnpj;
    private LocalDate startDate;
    private String emailAddress;
    private String phoneNumber;

    public Company(Long id, String businessName, String legalName, String cnpj, LocalDate startDate, String emailAddress, String phoneNumber) {
        this.id = id;
        this.businessName = businessName;
        this.legalName = legalName;
        this.cnpj = cnpj;
        this.startDate = startDate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    protected Company() {
    }

    public Long getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
