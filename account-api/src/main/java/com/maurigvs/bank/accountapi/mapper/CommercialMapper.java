package com.maurigvs.bank.accountapi.mapper;

import com.maurigvs.bank.accountapi.dto.CommercialRequest;
import com.maurigvs.bank.accountapi.model.Commercial;

import java.time.LocalDate;
import java.util.function.Function;

public class CommercialMapper implements Function<CommercialRequest, Commercial> {

    @Override
    public Commercial apply(CommercialRequest commercialRequest) {
        var initialBalance = Double.valueOf(0.0);
        var createdAt = LocalDate.now();

        return new Commercial(null,
                commercialRequest.customerCnpj(),
                initialBalance,
                commercialRequest.pinCode(),
                createdAt);
    }
}
