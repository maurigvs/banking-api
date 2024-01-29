package com.maurigvs.bank.accounts.repository;

import com.maurigvs.bank.accounts.model.ConsumerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerAccountRepository extends JpaRepository<ConsumerAccount, Long> {
}
