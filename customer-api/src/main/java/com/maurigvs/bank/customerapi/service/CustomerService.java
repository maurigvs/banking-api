package com.maurigvs.bank.customerapi.service;

import com.maurigvs.bank.customerapi.model.Customer;

interface CustomerService<T extends Customer> {

    void create(T t);

    T findByTaxId(String taxId);
}
