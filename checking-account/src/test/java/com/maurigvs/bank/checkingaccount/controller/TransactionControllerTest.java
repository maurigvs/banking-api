package com.maurigvs.bank.checkingaccount.controller;

import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.service.AccountService;
import com.maurigvs.bank.checkingaccount.service.TransactionService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @MockBean
    TransactionService transactionService;

    @Test
    void should_return_200_when_post_account_deposit() throws Exception {

        var accoundId = 234567L;
        var pinCode = 123456;
        var amount = 250.30;

        var account = new Account(accoundId, 123456L, pinCode, 100.00);
        given(accountService.authenticate(anyLong(), anyInt())).willReturn(account);

        mockMvc.perform(post("/account/234567/123456/deposit/250.30"))
                .andExpect(status().isAccepted());

        then(accountService).should(times(1)).authenticate(accoundId, pinCode);
        then(transactionService).should(times(1)).deposit(account, amount);

        then(accountService).shouldHaveNoMoreInteractions();
        then(transactionService).shouldHaveNoMoreInteractions();
    }
}