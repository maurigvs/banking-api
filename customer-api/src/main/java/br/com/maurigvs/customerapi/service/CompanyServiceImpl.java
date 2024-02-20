package br.com.maurigvs.customerapi.service;

import br.com.maurigvs.customerapi.model.Company;
import br.com.maurigvs.customerapi.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

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
