package com.maurigvs.bank.accountapi.mapper;

import com.maurigvs.bank.accountapi.dto.CommercialRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommercialMapperTest {

    @Test
    void should_return_Commercial_given_an_CommercialRequest() {
        var commercialRequest = new CommercialRequest("29382687000159", 123456);
        var balance = 0.0;
        var createdAt = LocalDate.now();

        var result = new CommercialMapper().apply(commercialRequest);

        assertNull(result.getId());
        assertEquals(commercialRequest.customerCnpj(), result.getCustomerCnpj());
        assertEquals(commercialRequest.pinCode(), result.getPinCode());
        assertEquals(balance, result.getBalance());
        assertEquals(createdAt, result.getCreatedAt());
    }
}