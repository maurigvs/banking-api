package br.com.maurigvs.banking.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

import br.com.maurigvs.banking.model.entity.Account;
import br.com.maurigvs.banking.model.entity.Customer;
import br.com.maurigvs.banking.repository.AccountRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    protected Account create(Customer customer){
        return repository.save(new Account(null, UUID.randomUUID(),0.00, customer));
    }

    protected void updateBalance(Long accountId, Double amount) {
        Account account = repository.getReferenceById(accountId);
        account.setBalance(Double.sum(account.getBalance(), amount));
        repository.save(account);
    }

    public List<Account> listAccounts() {
        return repository.findAll();
    }
}