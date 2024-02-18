package br.com.maurigvs.customer.mapper;

import br.com.maurigvs.customer.dto.CompanyRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyMapperTest {

    @Test
    void should_return_Company_given_an_CompanyRequest() {
        var request = new CompanyRequest(
                "Contoso",
                "Contoso Services Inc.",
                "29382687000159",
                "07/04/2004",
                "finance@contoso.com",
                "+351654358130");
        var startDate = LocalDate.of(2004,4,7);
        var createdAt = LocalDate.now();

        var result = new CompanyMapper().apply(request);

        assertNull(result.getId());
        assertEquals(request.businessName(), result.getBusinessName());
        assertEquals(request.legalName(), result.getLegalName());
        assertEquals(request.cnpj(), result.getCnpj());
        assertEquals(startDate, result.getStartDate());
        assertEquals(request.emailAddress(), result.getContactInfo().getEmailAddress());
        assertEquals(request.phoneNumber(), result.getContactInfo().getPhoneNumber());
        assertEquals(createdAt, result.getCreatedAt());
    }
}