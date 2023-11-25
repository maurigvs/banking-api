package br.com.maurigvs.banking.model;

import java.time.LocalDate;
import java.util.UUID;

public class Account {
    
    private Long id;
    private UUID key;
    private LocalDate openedAt;
    private Customer customer;

    public Account(UUID key, LocalDate openedAt, Customer customer) {
        this.key = key;
        this.openedAt = openedAt;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }
    public UUID getKey() {
        return key;
    }
    public LocalDate getOpenedAt() {
        return openedAt;
    }
    public Customer getCustomer() {
        return customer;
    }    
}
