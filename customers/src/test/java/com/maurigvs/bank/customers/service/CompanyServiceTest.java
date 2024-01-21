package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.controller.dto.CompanyRequest;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Company;
import com.maurigvs.bank.customers.repository.CustomerRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(classes = {CompanyService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @MockBean
    CustomerRepository customerRepository;

    @Captor
    ArgumentCaptor<Company> companyCaptor;

    @BeforeEach
    void setUp() {
        given(customerRepository.existsByTaxId(anyString())).willReturn(false);
    }

    @Test
    void should_create_company_successfully() throws Exception {

        var request = new CompanyRequest("72097237000143",
                "Company Services", "Company Services Ltd.",
                "03/05/2013", "john@wayne.com", "+5511984833929");

        companyService.createCompany(request);

        then(customerRepository).should().existsByTaxId(request.taxId());
        then(customerRepository).should().save(companyCaptor.capture());
        then(customerRepository).shouldHaveNoMoreInteractions();

        var company = companyCaptor.getValue();
        assertNull(company.getId());
        assertEquals(request.taxId(), company.getTaxId());
        assertEquals(LocalDate.now(), company.getSince());
        assertTrue(company.getEnabled());
        assertEquals(request.businessName(), company.getBusinessName());
        assertEquals(request.legalName(), company.getLegalName());
        assertEquals(LocalDate.of(2013,5,3), company.getStartDate());
        assertEquals(request.email(), company.getContactInfo().getEmail());
        assertEquals(request.phoneNumber(), company.getContactInfo().getPhone());
    }

    @Test
    void should_throw_BusinessException_when_company_has_less_than_6_months() {

        var startDate = LocalDate.now().minusMonths(6).plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        var request = new CompanyRequest("72097237000143",
                "Company Services", "Company Services Ltd.",
                startDate, "john@wayne.com", "+5511984833929");

        assertEquals("The company need to be older than 6 months",
                assertThrows(BusinessException.class, () ->
                        companyService.createCompany(request)).getLocalizedMessage());

        then(customerRepository).shouldHaveNoInteractions();
    }

    @Test
    void should_throw_BusinessException_when_company_already_exists() {

        var request = new CompanyRequest("72097237000143",
                "Company Services", "Company Services Ltd.",
                "03/05/2013", "john@wayne.com", "+5511984833929");

        given(customerRepository.existsByTaxId(anyString())).willReturn(true);

        assertEquals("Customer already exists",
                assertThrows(BusinessException.class, () ->
                        companyService.createCompany(request)).getLocalizedMessage());

        then(customerRepository).should().existsByTaxId(request.taxId());
        then(customerRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_BusinessException_when_startDate_has_invalid_format() {

        var request = new CompanyRequest("72097237000143",
                "Company Services", "Company Services Ltd.",
                "2013-05-03", "john@wayne.com", "+5511984833929");

        assertEquals("date must have the format: dd/MM/yyyy",
                assertThrows(BusinessException.class, () ->
                        companyService.createCompany(request)).getLocalizedMessage());

        then(customerRepository).shouldHaveNoInteractions();
    }
}