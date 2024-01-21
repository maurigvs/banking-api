package com.maurigvs.bank.accountholder.repository;

import com.maurigvs.bank.accountholder.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByCnpj(String cnpj);
}
