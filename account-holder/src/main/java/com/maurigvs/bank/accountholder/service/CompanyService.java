package com.maurigvs.bank.accountholder.service;

import com.maurigvs.bank.accountholder.controller.dto.CreateCompanyRequest;
import com.maurigvs.bank.accountholder.exception.BusinessRuleException;
import com.maurigvs.bank.accountholder.model.Company;
import com.maurigvs.bank.accountholder.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CompanyService extends AccountHolderService {

    private final CompanyRepository companyRepository;

    public void createCompany(CreateCompanyRequest request) throws Exception {

        var openingDate = localDateFrom(request.openingDate());

        if(companyNotOldEnough(openingDate))
            throw new BusinessRuleException("The company needs to be older than 6 months");

        if(companyAlreadyExists(request))
            throw new BusinessRuleException("The account holder already exists");

        companyRepository.save(new Company(null, LocalDate.now(), true,
                request.legalName(), request.businessName(), localDateFrom(request.openingDate()),
                request.cnpj(), request.contactEmail(), request.contactPhoneNumber()));
    }

    private boolean companyNotOldEnough(LocalDate openingDate) {
        return openingDate.isAfter(LocalDate.now().minusMonths(6));
    }

    private boolean companyAlreadyExists(CreateCompanyRequest request) {
        return companyRepository.existsByCnpj(request.cnpj());
    }
}