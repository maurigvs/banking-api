package br.maurigvs.banking.customer.repository;

import br.maurigvs.banking.customer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByTaxId(String taxId);
}
