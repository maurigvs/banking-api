package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Company;
import com.maurigvs.bank.customers.model.ContactInfo;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(classes = {CompanyService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyServiceTest {

    @Autowired
    private CompanyService service;

    @MockBean
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        given(repository.existsByTaxId(anyString())).willReturn(false);
    }

    @Test
    void should_create_company_successfully() throws Exception {

        var company = new Company(null, "72097237000143", LocalDate.now(), true,
                new ContactInfo("john@wayne.com", "+5511984833929"),
                "Company Services", "Company Services Ltd.",
                LocalDate.of(2023,5,3));

        service.create(company);

        then(repository).should().existsByTaxId(company.getTaxId());
        then(repository).should().save(company);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_BusinessException_when_company_has_less_than_6_months() {

        var startDate = LocalDate.now().minusMonths(6).plusDays(1);

        var company = new Company(null, "72097237000143", LocalDate.now(), true,
                new ContactInfo("john@wayne.com", "+5511984833929"),
                "Company Services", "Company Services Ltd.",
                startDate);

        assertEquals("The company need to be older than 6 months",
                assertThrows(BusinessException.class, () ->
                        service.create(company)).getLocalizedMessage());

        then(repository).shouldHaveNoInteractions();
    }

    @Test
    void should_throw_BusinessException_when_company_already_exists() {

        var company = new Company(null, "72097237000143", LocalDate.now(), true,
                new ContactInfo("john@wayne.com", "+5511984833929"),
                "Company Services", "Company Services Ltd.",
                LocalDate.of(2023,5,3));

        given(repository.existsByTaxId(anyString())).willReturn(true);

        assertEquals("Customer already exists",
                assertThrows(BusinessException.class, () ->
                        service.create(company)).getLocalizedMessage());

        then(repository).should().existsByTaxId(company.getTaxId());
        then(repository).shouldHaveNoMoreInteractions();
    }
}