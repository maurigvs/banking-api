package com.maurigvs.bank.transactionapi.service;

import com.maurigvs.bank.transactionapi.model.Customer;

public interface CustomerService {

    Customer findByTaxId(String taxId);
}
