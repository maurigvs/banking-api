package br.com.maurigvs.banking.model;

import java.time.LocalDate;

public class Customer {
    
    private Long id;
    private String taxId;
    private String name;
    private String surname;
    private LocalDate since;

    public Customer(String taxId, String name, String surname, LocalDate since) {
        this.taxId = taxId;
        this.name = name;
        this.surname = surname;
        this.since = since;
    }

    public Long getId() {
        return id;
    }
    public String getTaxId() {
        return taxId;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public LocalDate getSince() {
        return since;
    }
}