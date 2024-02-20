package com.maurigvs.bank.accountapi.dto;

public record ConsumerRequest(
        String customerCpf,
        Integer pinCode
) {
}
