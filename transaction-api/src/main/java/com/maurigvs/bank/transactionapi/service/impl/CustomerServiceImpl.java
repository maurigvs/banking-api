package com.maurigvs.bank.transactionapi.service.impl;

import com.maurigvs.bank.transactionapi.model.Customer;
import com.maurigvs.bank.transactionapi.repository.CustomerRepository;
import com.maurigvs.bank.transactionapi.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
class CustomerServiceImpl implements CustomerService {

    // TODO Replace with Grpc Call to customer-api
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer findByTaxId(String taxId) {
        var customer = new Customer(1L, taxId);
        return repository.save(customer);
    }
}
