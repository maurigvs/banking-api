package br.com.maurigvs.banking.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String taxId;
    
    private String name;
    
    private String surname;
    
    private LocalDate since;

    @OneToMany(mappedBy = "customer")
    private final List<Account> accounts = new ArrayList<>();

    protected Customer() {
    }

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

    public List<Account> getAccounts() {
        return accounts;
    }
}