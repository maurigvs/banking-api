package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.controller.dto.AccountRequest;
import com.maurigvs.bank.accounts.model.entity.Account;
import com.maurigvs.bank.accounts.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {AccountService.class})
class AccountServiceTest {

    @Autowired
    AccountService service;

    @MockBean
    AccountRepository repository;

    @Captor
    ArgumentCaptor<Account> captor;

    @Test
    void should_create_account_successfully() {

        var request = new AccountRequest("72097237000143", 123456);

        service.createAccount(request);

        verify(repository, times(1)).save(captor.capture());
        verifyNoMoreInteractions(repository);
        var result = captor.getValue();

        assertNull(result.getId());
        assertNotNull(result.getCustomerId());
        assertEquals(0.0, result.getBalance());
        assertEquals(request.pinCode(), result.getPinCode());
    }
}