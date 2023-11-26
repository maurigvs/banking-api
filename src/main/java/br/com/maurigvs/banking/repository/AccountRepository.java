package br.com.maurigvs.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maurigvs.banking.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}