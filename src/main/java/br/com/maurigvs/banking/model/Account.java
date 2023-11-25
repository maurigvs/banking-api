package br.com.maurigvs.banking.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID keyCode;
    
    private LocalDate openedAt;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    protected Account() {
    }

    public Account(UUID keyCode, LocalDate openedAt, Customer customer) {
        this.keyCode = keyCode;
        this.openedAt = openedAt;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public UUID getKeyCode() {
        return keyCode;
    }

    public LocalDate getOpenedAt() {
        return openedAt;
    }

    public Customer getCustomer() {
        return customer;
    }
}
