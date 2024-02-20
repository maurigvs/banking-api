package com.maurigvs.bank.accountapi.service;

import com.maurigvs.bank.accountapi.model.Account;

interface AccountService<T extends Account> {

    // create
    void create(T t);

    // read
    // update
    // delete
}
