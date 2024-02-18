package br.com.maurigvs.customer.service;

import br.com.maurigvs.customer.model.Company;
import br.com.maurigvs.customer.model.ContactInfo;
import br.com.maurigvs.customer.repository.CompanyRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {CompanyServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyServiceImplTest {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    void should_create_company() {
        var company = new Company(null,
                "Contoso",
                "Contoso Services Inc.",
                "29382687000159",
                LocalDate.of(2004,4,7),
                new ContactInfo("finance@contoso.com", "+351654358130"),
                LocalDate.now());

        companyService.create(company);

        verify(companyRepository, times(1)).save(company);
        verifyNoMoreInteractions(companyRepository);
    }
}
