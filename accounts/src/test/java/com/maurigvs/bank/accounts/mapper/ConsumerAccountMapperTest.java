package com.maurigvs.bank.accounts.mapper;

import com.maurigvs.bank.accounts.dto.ConsumerAccountRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ConsumerAccountMapperTest {

    @Test
    void should_return_ConsumerAccount_given_an_ConsumerAccountRequest() {

        var initialBalance = 0.0;
        var openingDate = LocalDate.now();
        var request = new ConsumerAccountRequest("86580512180", 123456);

        var result = new ConsumerAccountMapper().apply(request);

        assertNull(result.getId());
        assertEquals(request.taxId(), result.getTaxId());
        assertEquals(openingDate, result.getOpeningDate());
        assertEquals(initialBalance, result.getBalance());
        assertEquals(request.pinCode(), result.getPinCode());
    }
}