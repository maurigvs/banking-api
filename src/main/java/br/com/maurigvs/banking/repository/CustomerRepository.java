package br.com.maurigvs.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maurigvs.banking.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}