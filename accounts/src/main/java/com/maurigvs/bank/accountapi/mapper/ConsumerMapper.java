package com.maurigvs.bank.accountapi.mapper;

import com.maurigvs.bank.accountapi.dto.ConsumerRequest;
import com.maurigvs.bank.accountapi.model.Consumer;

import java.util.function.Function;

public class ConsumerMapper implements Function<ConsumerRequest, Consumer> {

    @Override
    public Consumer apply(ConsumerRequest request) {
        return new Consumer(
                null,
                request.customerCpf(),
                request.pinCode());
    }
}
