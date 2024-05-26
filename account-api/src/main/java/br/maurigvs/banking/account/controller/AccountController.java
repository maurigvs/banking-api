package br.maurigvs.banking.account.controller;

import br.maurigvs.banking.account.dto.AccountRequest;
import br.maurigvs.banking.account.dto.AccountResponse;
import br.maurigvs.banking.account.grpc.CustomerGrpcClient;
import br.maurigvs.banking.account.mapper.AccountMapper;
import br.maurigvs.banking.account.mapper.CustomerMapper;
import br.maurigvs.banking.account.service.AccountService;
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

    private final AccountService accountService;
    private final CustomerGrpcClient customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountResponse> postAccount(@RequestBody AccountRequest request){
        return customerService.create(CustomerMapper.toRequest(request.customerInfo()))
                .map(customerId -> AccountMapper.toEntity(request, customerId))
                .flatMap(accountService::create)
                .map(AccountMapper::toResponse);
    }
}
