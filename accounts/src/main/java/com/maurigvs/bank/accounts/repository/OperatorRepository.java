package com.maurigvs.bank.accounts.repository;

import com.maurigvs.bank.accounts.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
