package com.maurigvs.bank.accountapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class Commercial extends Account implements Serializable {

    private String customerCnpj;

    public Commercial(Long id, String customerCnpj, Double balance, Integer pinCode, LocalDate createdAt) {
        super(id, balance, pinCode, createdAt);
        this.customerCnpj = customerCnpj;
    }

    protected Commercial() {
        super();
    }

    public String getCustomerCnpj() {
        return customerCnpj;
    }
}
