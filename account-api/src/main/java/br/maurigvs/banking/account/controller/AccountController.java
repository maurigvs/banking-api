package br.maurigvs.banking.account.controller;

import br.maurigvs.banking.account.model.dto.AccountRequest;
import br.maurigvs.banking.account.model.dto.AccountResponse;
import br.maurigvs.banking.account.model.dto.TransactionResponse;
import br.maurigvs.banking.account.model.mapper.AccountMapper;
import br.maurigvs.banking.account.service.AccountService;
import br.maurigvs.banking.account.service.AggregatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AggregatorService aggregatorService;
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountResponse> postAccount(@RequestBody @Valid AccountRequest request){
        return aggregatorService.openAccount(request);
    }

    @GetMapping
    public Flux<AccountResponse> getAccountList(){
        return accountService.findAll().map(AccountMapper::toResponse);
    }

    @GetMapping("/{id}/transactions")
    public Flux<TransactionResponse> getTransactionListByAccountId(@PathVariable Long id) {
        return aggregatorService.getAccountStatement(id);
    }
}
