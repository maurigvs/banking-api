package com.maurigvs.bank.transactionapi.mapper;

import com.maurigvs.bank.transactionapi.dto.TransactionRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TransactionMapperTest {

    @Test
    void should_return_Transaction_given_an_TransactionRequest() {
        var transactionRequest = new TransactionRequest(
                "63592564528",
                1L,
                "CREDIT",
                "Initial deposit",
                100.00);

        var result = new TransactionMapper().apply(transactionRequest);

        assertNull(result.getId());
        assertEquals(transactionRequest.customerCpf(), result.getCustomerCpf());
        assertEquals(transactionRequest.accountNumber(), result.getAccountNumber());
        assertEquals(transactionRequest.operation(), result.getOperation());
        assertEquals(transactionRequest.description(), result.getDescription());
        assertEquals(transactionRequest.amount(), result.getAmount());
        assertNotNull(result.getDateTime());
        assertInstanceOf(ZonedDateTime.class, result.getDateTime());
    }
}