package br.maurigvs.banking.customer.controller;

import br.maurigvs.banking.customer.model.dto.CustomerRequest;
import br.maurigvs.banking.customer.model.dto.CustomerResponse;
import br.maurigvs.banking.customer.model.mapper.CustomerMapper;
import br.maurigvs.banking.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerResponse> postCustomer(@RequestBody @Valid CustomerRequest request){
        return customerService.create(CustomerMapper.toEntity(request))
                .map(CustomerMapper::toResponse);
    }

    @GetMapping
    public Flux<CustomerResponse> getCustomerList(){
        return customerService.findAll().map(CustomerMapper::toResponse);
    }
}
