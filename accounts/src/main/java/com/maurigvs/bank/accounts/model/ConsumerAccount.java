package com.maurigvs.bank.accounts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
@Getter
public class ConsumerAccount extends Account implements Serializable {

    public ConsumerAccount(Long id, String taxId, LocalDate openingDate, Double balance, Integer pinCode) {
        super(id, taxId, openingDate, balance, pinCode);
    }
}
