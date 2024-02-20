package com.maurigvs.bank.transactionapi.repository;

import com.maurigvs.bank.transactionapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
