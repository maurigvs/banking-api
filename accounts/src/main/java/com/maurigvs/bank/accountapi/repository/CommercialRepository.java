package com.maurigvs.bank.accountapi.repository;

import com.maurigvs.bank.accountapi.model.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialRepository extends JpaRepository<Commercial, Long> {
}
