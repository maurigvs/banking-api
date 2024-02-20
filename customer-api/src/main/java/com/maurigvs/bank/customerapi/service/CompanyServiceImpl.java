package com.maurigvs.bank.customerapi.service;

import com.maurigvs.bank.customerapi.model.Company;
import com.maurigvs.bank.customerapi.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Company company) {
        repository.save(company);
    }

    @Override
    public Company findByTaxId(String taxId) {
        return repository.findByCnpj(taxId).orElseThrow();
    }
}
