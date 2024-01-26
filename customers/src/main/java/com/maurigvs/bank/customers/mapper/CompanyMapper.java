package com.maurigvs.bank.customers.mapper;

import com.maurigvs.bank.customers.dto.CompanyRequest;
import com.maurigvs.bank.customers.model.Company;
import com.maurigvs.bank.customers.model.ContactInfo;

import java.util.function.Function;

public class CompanyMapper extends CustomerMapper implements Function<CompanyRequest, Company> {

    @Override
    public Company apply(CompanyRequest request) {

        var contactInfo = new ContactInfo(request.email(), request.phoneNumber());
        var startDate = new LocalDateParser().apply(request.startDate());

        return new Company(null, request.taxId(), DEFAULT_SINCE, DEFAULT_ENABLED,
                contactInfo, request.businessName(), request.legalName(), startDate);
    }
}