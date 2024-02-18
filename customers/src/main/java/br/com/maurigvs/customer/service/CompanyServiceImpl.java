package br.com.maurigvs.customer.service;

import br.com.maurigvs.customer.model.Company;
import br.com.maurigvs.customer.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void create(Company company) {
        companyRepository.save(company);
    }
}
