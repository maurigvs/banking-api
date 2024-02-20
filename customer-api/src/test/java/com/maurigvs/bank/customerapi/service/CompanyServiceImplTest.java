package com.maurigvs.bank.customerapi.service;

import com.maurigvs.bank.customerapi.model.Company;
import com.maurigvs.bank.customerapi.model.ContactInfo;
import com.maurigvs.bank.customerapi.repository.CompanyRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {CompanyServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyServiceImplTest {

    @Autowired
    private CompanyService service;

    @MockBean
    private CompanyRepository repository;

    @Test
    void should_create_company() {
        var company = new Company(null,
                "Contoso",
                "Contoso Services Inc.",
                "29382687000159",
                LocalDate.of(2004,4,7),
                new ContactInfo("finance@contoso.com", "+351654358130"),
                LocalDate.now());

        service.create(company);

        verify(repository, times(1)).save(company);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_Company_when_find_by_taxId() {
        var expected = new Company(1L,
                "Contoso",
                "Contoso Services Inc.",
                "29382687000159",
                LocalDate.of(2004,4,7),
                new ContactInfo("finance@contoso.com", "+351654358130"),
                LocalDate.now());
        var cnpj = "29382687000159";
        given(repository.findByCnpj(anyString())).willReturn(Optional.of(expected));

        var result = service.findByTaxId(cnpj);

        verify(repository, times(1)).findByCnpj(cnpj);
        verifyNoMoreInteractions(repository);
        assertSame(expected, result);
    }

    @Test
    void should_throw_Exception_when_company_not_found_by_taxId() {
        var cnpj = "29382687000159";
        var expected = "No value present";
        given(repository.findByCnpj(anyString())).willReturn(Optional.empty());

        var result = assertThrows(NoSuchElementException.class,
                () -> service.findByTaxId(cnpj));

        verify(repository, times(1)).findByCnpj(cnpj);
        verifyNoMoreInteractions(repository);
        assertEquals(expected, result.getMessage());
    }
}
