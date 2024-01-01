package com.maurigvs.bank.checkingaccount.repository;

import com.maurigvs.bank.checkingaccount.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}