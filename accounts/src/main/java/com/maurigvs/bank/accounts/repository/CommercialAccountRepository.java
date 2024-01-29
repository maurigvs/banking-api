package com.maurigvs.bank.accounts.repository;

import com.maurigvs.bank.accounts.model.CommercialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialAccountRepository extends JpaRepository<CommercialAccount, Long> {
}
