package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.model.Account;

public interface AccountService <T extends Account> {

    void create(T account);
}
