package com.maurigvs.bank.customers.repository;

import com.maurigvs.bank.customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByTaxId(String taxId);
}
