package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Company;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CompanyService implements CustomerService<Company> {

    private final CustomerRepository repository;

    @Override
    public void create(Company company) throws BusinessException {
        validateStartDate(company.getStartDate());
        validateAlreadyExists(company.getTaxId());
        repository.save(company);
    }

    private void validateAlreadyExists(String taxId) throws BusinessException {
        if(repository.existsByTaxId(taxId))
            throw new BusinessException("Customer already exists");
    }

    private void validateStartDate(LocalDate startDate) throws BusinessException {
        if(startDate.isAfter(LocalDate.now().minusMonths(6)))
            throw new BusinessException("The company need to be older than 6 months");
    }
}