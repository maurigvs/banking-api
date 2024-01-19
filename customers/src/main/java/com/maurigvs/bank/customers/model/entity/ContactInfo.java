package com.maurigvs.bank.customers.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ContactInfo implements Serializable {

    private String emailAddress;
    private String phoneNumber;

}
