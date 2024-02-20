package com.maurigvs.bank.transactionapi.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // TODO Convert to Enum DEBIT or CREDIT
    private String operation;

    private String description;

    private Double amount;

    private ZonedDateTime createdAt;

    @Transient
    private boolean verified;

    public Transaction(Long id, Customer customer, Account account, String operation, String description, Double amount, ZonedDateTime createdAt, boolean verified) {
        this.id = id;
        this.customer = customer;
        this.account = account;
        this.operation = operation;
        this.description = description;
        this.amount = amount;
        this.createdAt = createdAt;
        this.verified = verified;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Account getAccount() {
        return account;
    }

    public String getOperation() {
        return operation;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
