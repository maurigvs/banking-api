package com.maurigvs.bank.accounts.mapper;

import com.maurigvs.bank.accounts.dto.CommercialAccountRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommercialAccountMapperTest {

    @Test
    void should_return_CommercialAccount_given_an_CommercialAccountRequest() {

        var initialBalance = 0.0;
        var openingDate = LocalDate.now();
        var operatorRequest = new CommercialAccountRequest.OperatorRequest("John Snow",
                "john@snow.com", "932567436", 234567);
        var request = new CommercialAccountRequest("72097237000143", 123456, operatorRequest);

        var result = new CommercialAccountMapper().apply(request);

        assertNull(result.getId());
        assertEquals(request.taxId(), result.getTaxId());
        assertEquals(openingDate, result.getOpeningDate());
        assertEquals(initialBalance, result.getBalance());
        assertEquals(request.pinCode(), result.getPinCode());

        var operatorResult = result.getOperators().get(0);
        assertNull(operatorResult.getId());
        assertEquals(operatorRequest.name(), operatorResult.getName());
        assertEquals(operatorRequest.email(), operatorResult.getEmail());
        assertEquals(operatorRequest.phone(), operatorResult.getPhone());
        assertEquals(operatorRequest.pinCode(), operatorResult.getPinCode());
        assertSame(result, operatorResult.getAccount());
    }
}