package br.com.maurigvs.customer.mapper;

import br.com.maurigvs.customer.dto.CompanyRequest;
import br.com.maurigvs.customer.model.Company;
import br.com.maurigvs.customer.model.ContactInfo;

import java.time.LocalDate;
import java.util.function.Function;

public class CompanyMapper implements Function<CompanyRequest, Company> {

    @Override
    public Company apply(CompanyRequest request) {
        var startDate = new LocalDateParser().apply(request.startDate());
        var contactInfo = new ContactInfo(request.emailAddress(), request.phoneNumber());
        var createdAt = LocalDate.now();

        return new Company(null,
                request.businessName(),
                request.legalName(),
                request.cnpj(),
                startDate,
                contactInfo,
                createdAt);
    }
}
