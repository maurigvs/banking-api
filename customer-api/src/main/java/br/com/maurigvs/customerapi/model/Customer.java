package br.com.maurigvs.customerapi.model;

import jakarta.persistence.*;

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
