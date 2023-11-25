package br.com.maurigvs.banking.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maurigvs.banking.model.Account;
import br.com.maurigvs.banking.model.AccountRepository;
import br.com.maurigvs.banking.model.Customer;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Account openAccount(String taxId, String name, String surname){
        Account account = new Account(UUID.randomUUID(), LocalDate.now(), 
                            new Customer(taxId, name, surname, LocalDate.now()));
        return accountRepository.save(account);
    }
}