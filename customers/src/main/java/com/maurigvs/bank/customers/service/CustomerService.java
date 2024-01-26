package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.model.Customer;

public interface CustomerService<T extends Customer> {

    void create(T t) throws BusinessException;
}
