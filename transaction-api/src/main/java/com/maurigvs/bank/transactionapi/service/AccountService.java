package com.maurigvs.bank.transactionapi.service;

import com.maurigvs.bank.transactionapi.model.Account;

public interface AccountService {

    Account findById(Long id);
}
