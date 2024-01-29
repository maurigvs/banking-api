package com.maurigvs.bank.accounts.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
@Getter
public class CommercialAccount extends Account implements Serializable {

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Operator> operators = new ArrayList<>();

    public CommercialAccount(Long id, String taxId, LocalDate openingDate, Double balance, Integer pinCode) {
        super(id, taxId, openingDate, balance, pinCode);
    }
}
