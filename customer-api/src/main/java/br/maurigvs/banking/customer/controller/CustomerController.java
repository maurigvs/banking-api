package br.maurigvs.banking.customer.controller;

import br.maurigvs.banking.customer.model.dto.CustomerResponse;
import br.maurigvs.banking.customer.model.mapper.CustomerMapper;
import br.maurigvs.banking.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Flux<CustomerResponse> getCustomerList(){
        return customerService.findAll().map(CustomerMapper::toResponse);
    }
}
