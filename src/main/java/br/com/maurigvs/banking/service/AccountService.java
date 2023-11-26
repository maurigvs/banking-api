package br.com.maurigvs.banking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

import br.com.maurigvs.banking.model.Account;
import br.com.maurigvs.banking.model.Customer;
import br.com.maurigvs.banking.repository.AccountRepository;
import br.com.maurigvs.banking.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Account openAccount(String taxId, String name, String surname){
        Customer customer = new Customer(null, taxId, name, surname, LocalDate.now());
        Account account = new Account(null, UUID.randomUUID(), LocalDate.now(), customer);
        customerRepository.save(customer);
        return accountRepository.save(account);
    }

    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }
}