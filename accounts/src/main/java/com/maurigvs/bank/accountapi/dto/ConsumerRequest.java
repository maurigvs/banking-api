package com.maurigvs.bank.accountapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record ConsumerRequest(

        @Size(min = 11, max = 11)
        String customerCpf,

        @Min(value = 100000)
        @Max(value = 999999)
        Integer pinCode
) {
}
