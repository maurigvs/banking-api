package com.maurigvs.bank.accountholder.service;

import com.maurigvs.bank.accountholder.Mocks;
import com.maurigvs.bank.accountholder.controller.dto.CreateCompanyRequest;
import com.maurigvs.bank.accountholder.exception.BusinessRuleException;
import com.maurigvs.bank.accountholder.model.Company;
import com.maurigvs.bank.accountholder.repository.CompanyRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

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
        given(companyRepository.existsByTaxIdNumber(anyString())).willReturn(false);
    }

    @Test
    void should_create_company_successfully() throws Exception {

        var request = Mocks.createCompanyRequest();
        var companyCreated = Mocks.company();
        given(companyRepository.save(any(Company.class))).willReturn(companyCreated);

        companyService.createCompany(request);

        then(companyRepository).should(times(1)).existsByTaxIdNumber(request.taxIdNumber());
        then(companyRepository).should(times(1)).save(companyCaptor.capture());
        then(companyRepository).shouldHaveNoMoreInteractions();

        var company = companyCaptor.getValue();
        assertThat(company.getId()).isNull();
        assertThat(company.getCustomerSince()).isEqualTo(LocalDate.now());
        assertThat(company.isEnabled()).isTrue();
        assertThat(company.getLegalName()).isEqualTo(request.legalName());
        assertThat(company.getBusinessName()).isEqualTo(request.businessName());
        assertThat(company.getOpeningDate()).isEqualTo(LocalDate.of(2013,5,3));
        assertThat(company.getTaxIdNumber()).isEqualTo(request.taxIdNumber());
        assertThat(company.getEmail()).isEqualTo(request.contactEmail());
        assertThat(company.getPhoneNumber()).isEqualTo(request.contactPhoneNumber());
    }

    @Test
    void should_throw_exception_if_company_already_exists() {

        given(companyRepository.existsByTaxIdNumber(anyString())).willReturn(true);

        assertThatExceptionOfType(BusinessRuleException.class)
                .isThrownBy(() -> companyService.createCompany(Mocks.createCompanyRequest()))
                .withMessage("The account holder already exists");

        then(companyRepository).should(times(1)).existsByTaxIdNumber("86580512180");
        then(companyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_exception_if_birth_date_is_invalid_when_create_company() {

        var request = new CreateCompanyRequest("Company Services",
                "Company Services Ltd.",
                "2013-05-03",
                "86580512180",
                "john@wayne.com",
                "+5511984833929");

        assertThatExceptionOfType(BusinessRuleException.class)
                .isThrownBy(() -> companyService.createCompany(request))
                .withMessage("The date must have the format: dd/MM/yyyy");
    }
}