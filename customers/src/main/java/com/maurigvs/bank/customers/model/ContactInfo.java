package com.maurigvs.bank.customers.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Getter
public class ContactInfo implements Serializable {

    private String email;
    private String phone;
}
