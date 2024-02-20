package com.maurigvs.bank.transactionapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerCpf;
    private Long accountNumber;
    private String operation;
    private String description;
    private Double amount;
    private ZonedDateTime dateTime;

    public Transaction(Long id, String customerCpf, Long accountNumber, String operation, String description, Double amount, ZonedDateTime dateTime) {
        this.id = id;
        this.customerCpf = customerCpf;
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.description = description;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public Long getAccountNumber() {
        return accountNumber;
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

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
