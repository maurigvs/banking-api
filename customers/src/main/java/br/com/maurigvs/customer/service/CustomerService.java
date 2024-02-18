package br.com.maurigvs.customer.service;

import br.com.maurigvs.customer.model.Customer;

public interface CustomerService<T extends Customer> {

    void create(T t);
}
