package com.maurigvs.bank.transactionapi.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double balamce;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Transaction> transactionList = new ArrayList<>();

    public Account(Long id, Customer customer, Double balamce) {
        this.id = id;
        this.customer = customer;
        this.balamce = balamce;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Double getBalamce() {
        return balamce;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
