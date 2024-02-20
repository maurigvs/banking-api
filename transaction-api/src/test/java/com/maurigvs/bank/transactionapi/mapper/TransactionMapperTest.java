package com.maurigvs.bank.transactionapi.mapper;

import com.maurigvs.bank.transactionapi.dto.TransactionRequest;
import com.maurigvs.bank.transactionapi.model.Account;
import com.maurigvs.bank.transactionapi.model.Customer;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TransactionMapperTest {

    @Test
    void should_return_Transaction_given_an_TransactionRequest() {
        var customer = new Customer(1L, "63592564528");
        var account = new Account(1L, 0.0);

        var transactionRequest = new TransactionRequest(
                "63592564528",
                1L,
                "CREDIT",
                "Initial deposit",
                100.00);

        var result = new TransactionMapper(customer, account).apply(transactionRequest);

        assertNull(result.getId());
        assertSame(customer, result.getCustomer());
        assertSame(account, result.getAccount());
        assertEquals(transactionRequest.operation(), result.getOperation());
        assertEquals(transactionRequest.description(), result.getDescription());
        assertEquals(transactionRequest.amount(), result.getAmount());
        assertNotNull(result.getCreatedAt());
        assertInstanceOf(ZonedDateTime.class, result.getCreatedAt());
    }
}