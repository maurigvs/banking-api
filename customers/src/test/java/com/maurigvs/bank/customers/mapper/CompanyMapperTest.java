package com.maurigvs.bank.customers.mapper;

import com.maurigvs.bank.customers.dto.CompanyRequest;
import com.maurigvs.bank.customers.mapper.CompanyMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyMapperTest {

    @Test
    void should_return_Company_given_an_CompanyRequest() {

        var request = new CompanyRequest("72097237000143", "Company Services",
                "Company Services Ltd.", "03/05/2013", "john@wayne.com", "+5511984833929");
        var since = LocalDate.now();
        var startDate = LocalDate.of(2013,5,3);

        var result = new CompanyMapper().apply(request);

        assertNull(result.getId());
        assertEquals(request.taxId(), result.getTaxId());
        assertEquals(since, result.getSince());
        assertTrue(result.getEnabled());
        assertEquals(request.businessName(), result.getBusinessName());
        assertEquals(request.legalName(), result.getLegalName());
        assertEquals(startDate, result.getStartDate());
        assertEquals(request.email(), result.getContactInfo().getEmail());
        assertEquals(request.phoneNumber(), result.getContactInfo().getPhone());
    }
}