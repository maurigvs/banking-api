package com.maurigvs.bank.accountapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class Consumer extends Account implements Serializable {

    private String customerCpf;

    public Consumer(Long id, String customerCpf, Double balance, Integer pinCode, LocalDate createdAt) {
        super(id, balance, pinCode, createdAt);
        this.customerCpf = customerCpf;
    }

    protected Consumer() {
        super();
    }

    public String getCustomerCpf() {
        return customerCpf;
    }
}
