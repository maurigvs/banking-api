package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {AccountService.class})
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @Captor
    ArgumentCaptor<Account> accountArgumentCaptor;

    @Test
    void should_open_account_successfully() {

        var request = new OpenAccountRequest(1L, 123456);
        given(accountRepository.save(any(Account.class))).willReturn(new Account(1L, 1L, 123456));

        accountService.openAccount(request);

        then(accountRepository).should(times(1)).save(accountArgumentCaptor.capture());
        then(accountRepository).shouldHaveNoMoreInteractions();
        var account = accountArgumentCaptor.getValue();
        assertNull(account.getId());
        assertEquals(request.accountHolderId(), account.getAccountHolderId());
        assertEquals(request.pinCode(), account.getPinCode());
    }
}