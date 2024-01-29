package com.maurigvs.bank.accounts.mapper;

import com.maurigvs.bank.accounts.dto.CommercialAccountRequest;
import com.maurigvs.bank.accounts.model.Operator;
import com.maurigvs.bank.accounts.model.CommercialAccount;

import java.time.LocalDate;
import java.util.function.Function;

public class CommercialAccountMapper implements Function<CommercialAccountRequest, CommercialAccount> {

    @Override
    public CommercialAccount apply(CommercialAccountRequest request) {
        var account = new CommercialAccount(null, request.taxId(), LocalDate.now(), 0.0, request.pinCode());
        var operator = applyOperator(request.operatorRequest(), account);
        account.getOperators().add(operator);

        return account;
    }

    private Operator applyOperator(CommercialAccountRequest.OperatorRequest request,
                                   CommercialAccount account) {
        return new Operator(null,
                request.name(),
                request.email(),
                request.phone(),
                request.pinCode(),
                account);
    }
}
