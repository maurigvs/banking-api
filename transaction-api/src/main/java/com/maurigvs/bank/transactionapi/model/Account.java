package com.maurigvs.bank.transactionapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    private Long id;

    private Double balamce;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Transaction> transactionList = new ArrayList<>();

    public Account(Long id, Double balamce) {
        this.id = id;
        this.balamce = balamce;
    }

    public Long getId() {
        return id;
    }

    public Double getBalamce() {
        return balamce;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
