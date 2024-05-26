package br.maurigvs.banking.account.service;

import br.maurigvs.banking.account.dto.AccountRequest;
import br.maurigvs.banking.account.dto.AccountResponse;
import br.maurigvs.banking.account.grpc.CustomerGrpcClient;
import br.maurigvs.banking.account.mapper.AccountMapper;
import br.maurigvs.banking.account.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class AggregatorServiceImpl implements AggregatorService {

    private final AccountService accountService;
    private final CustomerGrpcClient customerService;

    @Override
    public Mono<AccountResponse> openAccount(AccountRequest request) {
        return customerService.create(CustomerMapper.toRequest(request.customerInfo()))
                .map(customerId -> AccountMapper.toEntity(request, customerId))
                .flatMap(accountService::create)
                .map(AccountMapper::toResponse);
    }
}
