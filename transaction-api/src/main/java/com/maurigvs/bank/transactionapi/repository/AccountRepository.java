package com.maurigvs.bank.transactionapi.repository;

import com.maurigvs.bank.transactionapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
