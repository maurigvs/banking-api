package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@SpringBootTest(classes = {BankServiceImpl.class})
class BankServiceTest {

    @Autowired
    BankService service;

    @MockBean
    AccountService accounts;

    @MockBean
    TransactionService transactions;

    @Test
    void should_open_account() throws BusinessRuleException {

        var request = new OpenAccountRequest(123L, 250.00, 234567);
        var description = "Initial deposit";
        var account = new Account(123L, 123L, 234567, 0.0);
        given(accounts.openAccount(any(OpenAccountRequest.class))).willReturn(account);

        service.openAccount(request);

        then(accounts).should().checkElegibility(request);
        then(accounts).should().openAccount(request);
        then(transactions).should().credit(account, description, request.initialDeposit());
        then(accounts).should().creditAmount(account, request.initialDeposit());
        verifyNoMoreInteractions(accounts, transactions);
    }

    @Test
    void should_make_deposit() throws BusinessRuleException {

        var accountId = 123L;
        var pinCode = 234567;
        var amount = 234.00;
        var account = new Account(123L, 123L, 234567, 150.00);
        var description = "Cash Deposit";
        given(accounts.authenticate(anyLong(), anyInt())).willReturn(account);

        service.makeDeposit(accountId, pinCode, amount);

        then(accounts).should().authenticate(accountId, pinCode);
        then(transactions).should().credit(account, description, amount);
        then(accounts).should().creditAmount(account, amount);
        verifyNoMoreInteractions(accounts, transactions);
    }

    @Test
    void should_make_withdraw() throws BusinessRuleException {

        var accountId = 123L;
        var pinCode = 234567;
        var amount = 70.00;
        var account = new Account(123L, 123L, 234567, 150.00);
        var description = "ATM Withdraw";
        given(accounts.authenticate(anyLong(), anyInt())).willReturn(account);

        service.makeWithdraw(accountId, pinCode, amount);

        then(accounts).should().authenticate(accountId, pinCode);
        then(transactions).should().debit(account, description, amount);
        then(accounts).should().debitAmount(account, amount);
        verifyNoMoreInteractions(accounts, transactions);
    }
}