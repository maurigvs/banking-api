package com.maurigvs.bank.accountapi.mapper;

import com.maurigvs.bank.accountapi.dto.ConsumerRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerMapperTest {

    @Test
    void should_return_Consumer_given_an_ConsumerRequest() {
        var consumerRequest = new ConsumerRequest("63592564528", 123456);

        var result = new ConsumerMapper().apply(consumerRequest);

        assertNull(result.getId());
        assertEquals(consumerRequest.customerCpf(), result.getCustomerCpf());
        assertEquals(consumerRequest.pinCode(), result.getPinCode());
    }
}