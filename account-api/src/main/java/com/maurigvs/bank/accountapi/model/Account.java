package com.maurigvs.bank.accountapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double balance;
    private Integer pinCode;
    private LocalDate createdAt;

    public Account(Long id, Double balance, Integer pinCode, LocalDate createdAt) {
        this.id = id;
        this.balance = balance;
        this.pinCode = pinCode;
        this.createdAt = createdAt;
    }

    protected Account() {
    }

    public Long getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
