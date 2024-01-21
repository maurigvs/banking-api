package com.maurigvs.bank.customers.repository;

import com.maurigvs.bank.customers.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByCnpj(String cnpj);
}
