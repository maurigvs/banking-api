package br.com.maurigvs.customerapi.service;

import br.com.maurigvs.customerapi.model.Customer;

interface CustomerService<T extends Customer> {

    void create(T t);

    T findByTaxId(String taxId);
}
