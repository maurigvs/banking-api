package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.controller.dto.PostCompanyDto;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Company;
import com.maurigvs.bank.customers.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CompanyService extends CustomerService {

    private final CompanyRepository companyRepository;

    public void createCompany(PostCompanyDto request) throws Exception {

        var openingDate = localDateFrom(request.openingDate());

        if(companyNotOldEnough(openingDate))
            throw new BusinessException("The company needs to be older than 6 months");

        if(companyAlreadyExists(request))
            throw new BusinessException("The account holder already exists");

        companyRepository.save(new Company(null, LocalDate.now(), true,
                request.legalName(), request.businessName(), localDateFrom(request.openingDate()),
                request.cnpj(), request.contactEmail(), request.contactPhoneNumber()));
    }

    private boolean companyNotOldEnough(LocalDate openingDate) {
        return openingDate.isAfter(LocalDate.now().minusMonths(6));
    }

    private boolean companyAlreadyExists(PostCompanyDto request) {
        return companyRepository.existsByCnpj(request.cnpj());
    }
}