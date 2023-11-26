package br.com.maurigvs.banking.service;

import org.springframework.stereotype.Service;

import br.com.maurigvs.banking.exception.BusinessException;
import br.com.maurigvs.banking.model.entity.Account;
import br.com.maurigvs.banking.model.entity.Customer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperationsService {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    
    @Transactional
    public Account openAccount(String taxId, String name, String surname, Double initialDeposit) throws BusinessException {
        Customer customer = customerService.create(taxId, name, surname);
        Account account = accountService.create(customer);
        transactionService.create("Initial deposit", initialDeposit, account);
        accountService.updateBalance(account.getId(), initialDeposit);
        return account;
    }
}