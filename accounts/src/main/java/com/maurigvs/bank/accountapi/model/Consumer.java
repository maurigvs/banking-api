package com.maurigvs.bank.accountapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerCpf;

    private Integer pinCode;

    public Consumer(Long id, String customerCpf, Integer pinCode) {
        this.id = id;
        this.customerCpf = customerCpf;
        this.pinCode = pinCode;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public Integer getPinCode() {
        return pinCode;
    }
}
