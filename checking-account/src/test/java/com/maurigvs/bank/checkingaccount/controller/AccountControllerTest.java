package com.maurigvs.bank.checkingaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.service.AccountService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    void should_return_201_when_post_account() throws Exception {

        var request = new OpenAccountRequest(123L, 123456);
        willDoNothing().given(accountService).openAccount(any(OpenAccountRequest.class));

        mockMvc.perform(post("/checking-account/consumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ofJsonFrom(request)))
                .andExpect(status().isCreated());

        then(accountService).should(times(1)).openAccount(eq(request));
        then(accountService).shouldHaveNoMoreInteractions();
    }

    private static String ofJsonFrom(Object object) throws Exception {
        final var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om.writeValueAsString(object);
    }
}