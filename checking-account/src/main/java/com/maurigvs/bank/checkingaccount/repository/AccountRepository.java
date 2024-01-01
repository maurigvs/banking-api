package com.maurigvs.bank.checkingaccount.repository;

import com.maurigvs.bank.checkingaccount.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
