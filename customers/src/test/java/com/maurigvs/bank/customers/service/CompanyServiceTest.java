package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.mock.Mocks;
import com.maurigvs.bank.customers.controller.dto.PostCompanyDto;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Company;
import com.maurigvs.bank.customers.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(classes = {CompanyService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @MockBean
    CompanyRepository companyRepository;

    @Captor
    ArgumentCaptor<Company> companyCaptor;

    @BeforeEach
    void setUp() {
        given(companyRepository.existsByCnpj(anyString())).willReturn(false);
    }

    @Test
    void should_create_company_successfully() throws Exception {

        var request = Mocks.ofCreateCompanyRequest();

        var companyCreated = new Company(1L, LocalDate.now(), true,
                "Company Services", "Company Services Ltd.",
                LocalDate.of(2013,5,3), "72097237000143",
                "john@wayne.com", "+5511984833929");

        given(companyRepository.save(any(Company.class))).willReturn(companyCreated);

        companyService.createCompany(request);

        then(companyRepository).should().existsByCnpj(request.cnpj());
        then(companyRepository).should().save(companyCaptor.capture());
        then(companyRepository).shouldHaveNoMoreInteractions();

        var company = companyCaptor.getValue();
        assertThat(company.getId()).isNull();
        assertThat(company.getCustomerSince()).isEqualTo(LocalDate.now());
        assertThat(company.isEnabled()).isTrue();
        assertThat(company.getLegalName()).isEqualTo(request.legalName());
        assertThat(company.getBusinessName()).isEqualTo(request.businessName());
        assertThat(company.getOpeningDate()).isEqualTo(LocalDate.of(2013,5,3));
        assertThat(company.getCnpj()).isEqualTo(request.cnpj());
        assertThat(company.getEmail()).isEqualTo(request.contactEmail());
        assertThat(company.getPhoneNumber()).isEqualTo(request.contactPhoneNumber());
    }

    @Test
    void should_throw_exception_if_company_newer_than_6_months() {

        var openingDate = LocalDate.now().minusMonths(6).plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        var request = new PostCompanyDto("Company Services",
                "Company Services Ltd.", openingDate, "86580512180",
                "john@wayne.com", "+5511984833929");

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> companyService.createCompany(request))
                .withMessage("The company needs to be older than 6 months");

        then(companyRepository).shouldHaveNoInteractions();
    }

    @Test
    void should_throw_exception_if_company_already_exists() {

        var request = Mocks.ofCreateCompanyRequest();
        given(companyRepository.existsByCnpj(anyString())).willReturn(true);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> companyService.createCompany(request))
                .withMessage("The account holder already exists");

        then(companyRepository).should().existsByCnpj(request.cnpj());
        then(companyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_exception_if_birth_date_is_invalid_when_create_company() {

        var request = new PostCompanyDto("Company Services",
                "Company Services Ltd.", "2013-05-03", "86580512180",
                "john@wayne.com", "+5511984833929");

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> companyService.createCompany(request))
                .withMessage("The date must have the format: dd/MM/yyyy");

        then(companyRepository).shouldHaveNoInteractions();
    }
}