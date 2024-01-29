package com.maurigvs.bank.accounts.mapper;

import com.maurigvs.bank.accounts.dto.ConsumerAccountRequest;
import com.maurigvs.bank.accounts.model.ConsumerAccount;

import java.time.LocalDate;
import java.util.function.Function;

public class ConsumerAccountMapper implements Function<ConsumerAccountRequest, ConsumerAccount> {

    @Override
    public ConsumerAccount apply(ConsumerAccountRequest request) {
        return new ConsumerAccount(null,
                request.taxId(),
                LocalDate.now(),
                0.0,
                request.pinCode());
    }
}
