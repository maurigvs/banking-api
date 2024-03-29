package com.maurigvs.bank.transactionapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    private Long id;

    private String taxId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Transaction> transactionList = new ArrayList<>();

    public Customer(Long id, String taxId) {
        this.id = id;
        this.taxId = taxId;
    }

    public Long getId() {
        return id;
    }

    public String getTaxId() {
        return taxId;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
