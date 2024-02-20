package com.maurigvs.bank.accountapi.mapper;

import com.maurigvs.bank.accountapi.dto.ConsumerRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ConsumerMapperTest {

    @Test
    void should_return_Consumer_given_an_ConsumerRequest() {
        var consumerRequest = new ConsumerRequest("63592564528", 123456);
        var balance = 0.0;
        var createdAt = LocalDate.now();

        var result = new ConsumerMapper().apply(consumerRequest);

        assertNull(result.getId());
        assertEquals(consumerRequest.customerCpf(), result.getCustomerCpf());
        assertEquals(consumerRequest.pinCode(), result.getPinCode());
        assertEquals(balance, result.getBalance());
        assertEquals(createdAt, result.getCreatedAt());
    }
}
