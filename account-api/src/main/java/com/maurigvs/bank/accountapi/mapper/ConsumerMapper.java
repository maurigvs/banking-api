package com.maurigvs.bank.accountapi.mapper;

import com.maurigvs.bank.accountapi.dto.ConsumerRequest;
import com.maurigvs.bank.accountapi.model.Consumer;

import java.time.LocalDate;
import java.util.function.Function;

public class ConsumerMapper implements Function<ConsumerRequest, Consumer> {

    @Override
    public Consumer apply(ConsumerRequest request) {
        var initialBalance = Double.valueOf(0.0);
        var createdAt = LocalDate.now();

        return new Consumer(null,
                request.customerCpf(),
                initialBalance,
                request.pinCode(),
                createdAt);
    }
}
