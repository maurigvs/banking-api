package com.maurigvs.bank.accounts.controller;

import com.maurigvs.bank.accounts.mock.Mocks;
import com.maurigvs.bank.accounts.model.dto.AccountRequest;
import com.maurigvs.bank.accounts.service.AccountService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AccountService accountService;

    @Test
    void should_return_created_when_post_account_successfully() throws Exception {

        var request = new AccountRequest("72097237000143", 123456);

        mockMvc.perform(post("/account/commercial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isCreated());

        verify(accountService, times(1)).openAccount(eq(request));
        verifyNoMoreInteractions(accountService);
    }
}