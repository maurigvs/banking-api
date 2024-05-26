package br.maurigvs.banking.account.controller;

import br.maurigvs.banking.account.dto.AccountRequest;
import br.maurigvs.banking.account.dto.AccountResponse;
import br.maurigvs.banking.account.service.AggregatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AggregatorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountResponse> postAccount(@RequestBody @Valid AccountRequest request){
        return service.openAccount(request);
    }
}
