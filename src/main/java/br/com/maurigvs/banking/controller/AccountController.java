package br.com.maurigvs.banking.controller;

import br.com.maurigvs.banking.model.dto.AccountCreated;
import br.com.maurigvs.banking.model.dto.AccountRequest;
import br.com.maurigvs.banking.model.dto.AccountResponse;
import br.com.maurigvs.banking.exception.BusinessException;
import br.com.maurigvs.banking.model.entity.Account;
import br.com.maurigvs.banking.service.AccountService;
import br.com.maurigvs.banking.service.OperationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    
    private final AccountService accountService;
    private final OperationsService operationsService;

    @PostMapping("/open")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountCreated postAccount(@RequestBody AccountRequest request) throws BusinessException{
        Account account = operationsService.openAccount(
                request.getTaxId(),
                request.getName(),
                request.getSurname(),
                request.getInitialDeposit()
        );
        return new AccountCreated(account);
    }

    @GetMapping("/list")
    public List<AccountResponse> getAllAccounts(){
        return accountService.listAccounts().stream()
            .map(AccountResponse::new).collect(Collectors.toList());
    }
}