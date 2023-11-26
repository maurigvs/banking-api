package br.com.maurigvs.banking.controller;

import br.com.maurigvs.banking.controller.dto.AccountCreated;
import br.com.maurigvs.banking.controller.dto.AccountRequest;
import br.com.maurigvs.banking.controller.dto.AccountResponse;
import br.com.maurigvs.banking.model.Account;
import br.com.maurigvs.banking.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    
    private final AccountService accountService;

    @PostMapping("/open")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public AccountCreated postAccount(@RequestBody AccountRequest request){
        Account account = accountService.openAccount(
                request.getTaxId(),
                request.getName(),
                request.getSurname()
        );
        return new AccountCreated(account);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<AccountResponse> getAllAccounts(){
        return accountService.listAccounts().stream()
            .map(AccountResponse::new).collect(Collectors.toList());
    }
}