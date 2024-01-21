package com.maurigvs.bank.accounts.repository;

import com.maurigvs.bank.accounts.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
