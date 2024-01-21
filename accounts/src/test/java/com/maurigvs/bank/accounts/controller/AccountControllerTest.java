package com.maurigvs.bank.accounts.controller;

import com.maurigvs.bank.accounts.controller.dto.AccountRequest;
import com.maurigvs.bank.accounts.controller.dto.ExceptionResponse;
import com.maurigvs.bank.accounts.mock.Mocks;
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

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AccountService service;

    @Test
    void should_return_created_when_post_account_successfully() throws Exception {

        var request = new AccountRequest("72097237000143", 123456);

        mockMvc.perform(post("/account/commercial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isCreated());

        verify(service, times(1)).createAccount(request);
        verifyNoMoreInteractions(service);
    }

    @Test
    void should_return_bad_request_when_MethodArgumentNotValidException_is_thrown() throws Exception {

        var request = new AccountRequest("12345", 1111);

        var response = new ExceptionResponse("Bad Request",
                List.of("pinCode must have 6 digits", "taxId must have 14 digits"));

        mockMvc.perform(post("/account/commercial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response), true));

        verifyNoInteractions(service);
    }
}