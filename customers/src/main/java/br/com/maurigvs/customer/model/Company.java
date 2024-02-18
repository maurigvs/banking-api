package br.com.maurigvs.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "customer_id")
public class Company extends Customer implements Serializable {

    private String businessName;
    private String legalName;
    private String cnpj;
    private LocalDate startDate;

    public Company(Long id, String businessName, String legalName, String cnpj, LocalDate startDate, String emailAddress, String phoneNumber, LocalDate createdAt) {
        super(id, emailAddress, phoneNumber, createdAt);
        this.businessName = businessName;
        this.legalName = legalName;
        this.cnpj = cnpj;
        this.startDate = startDate;
    }

    protected Company() {
        super();
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
}
