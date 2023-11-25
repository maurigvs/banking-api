package br.com.maurigvs.banking.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.maurigvs.banking.model.Account;
import br.com.maurigvs.banking.model.AccountRepository;
import br.com.maurigvs.banking.model.Customer;
import br.com.maurigvs.banking.model.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Account openAccount(String taxId, String name, String surname){
        Customer customer = new Customer(taxId, name, surname, LocalDate.now());
        Account account = new Account(UUID.randomUUID(), LocalDate.now(), customer);
        customerRepository.save(customer);
        return accountRepository.save(account);
    }
}