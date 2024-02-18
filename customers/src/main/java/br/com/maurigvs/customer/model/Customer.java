package br.com.maurigvs.customer.model;

import jakarta.persistence.Embedded;
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
public abstract class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ContactInfo contactInfo;

    private LocalDate createdAt;

    Customer(Long id, ContactInfo contactInfo, LocalDate createdAt) {
        this.id = id;
        this.contactInfo = contactInfo;
        this.createdAt = createdAt;
    }

    protected Customer() {
    }

    public Long getId() {
        return id;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
