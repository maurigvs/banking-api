package com.maurigvs.bank.customers.mapper;

import java.time.LocalDate;

abstract class CustomerMapper {

    static final LocalDate DEFAULT_SINCE = LocalDate.now();
    static final Boolean DEFAULT_ENABLED = Boolean.TRUE;

    CustomerMapper() {}
}
