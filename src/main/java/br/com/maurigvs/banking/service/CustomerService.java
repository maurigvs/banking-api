package br.com.maurigvs.banking.service;

import org.springframework.stereotype.Service;

import br.com.maurigvs.banking.exception.BusinessException;
import br.com.maurigvs.banking.model.entity.Customer;
import br.com.maurigvs.banking.repository.CustomerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    
    protected Customer create(String taxId, String name, String surname) throws BusinessException {
        if(repository.existsByTaxId(taxId))
            throw new BusinessException("Customer already exists");

        return repository.save(new Customer(null, taxId, name, surname));
    }
}