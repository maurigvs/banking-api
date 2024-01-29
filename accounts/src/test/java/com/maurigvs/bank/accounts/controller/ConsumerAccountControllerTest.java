package com.maurigvs.bank.accounts.controller;

import com.maurigvs.bank.accounts.dto.ConsumerAccountRequest;
import com.maurigvs.bank.accounts.dto.ExceptionResponse;
import com.maurigvs.bank.accounts.mock.Mocks;
import com.maurigvs.bank.accounts.model.ConsumerAccount;
import com.maurigvs.bank.accounts.service.ConsumerAccountService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsumerAccountController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ConsumerAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsumerAccountService service;

    @Test
    void should_return_created_when_post_consumer_account() throws Exception {

        var request = new ConsumerAccountRequest("86580512180", 123456);

        mockMvc.perform(post("/account/consumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isCreated());

        then(service).should().create(any(ConsumerAccount.class));
        then(service).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_MethodArgumentNotValidException_is_thrown() throws Exception {

        var request = new ConsumerAccountRequest(null, null);
        var messages = List.of("pinCode is required", "taxId is required");

        var response = new ExceptionResponse("Bad Request", messages);

        mockMvc.perform(post("/account/consumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response)));

        then(service).shouldHaveNoInteractions();
    }
}