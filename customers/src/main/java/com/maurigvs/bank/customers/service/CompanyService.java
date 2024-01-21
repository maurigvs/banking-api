package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.controller.dto.CompanyRequest;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Company;
import com.maurigvs.bank.customers.model.ContactInfo;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompanyService extends CustomerService {

    public CompanyService(CustomerRepository customerRepository) {
        super(customerRepository);
    }

    public void createCompany(CompanyRequest request) throws BusinessException {

        var startDate = parseLocalDate(request.startDate());

        if(isNewerThanSixMonths(startDate))
            throw new BusinessException("The company need to be older than 6 months");

        if(existsByTaxId(request.taxId()))
            throw new BusinessException("Customer already exists");

        createCustomer(
            new Company(null, request.taxId(), LocalDate.now(), true,
                    new ContactInfo(request.email(), request.phoneNumber()),
                    request.businessName(), request.legalName(), startDate)
        );
    }

    private boolean isNewerThanSixMonths(LocalDate localDate) {
        return localDate.isAfter(LocalDate.now().minusMonths(6));
    }
}