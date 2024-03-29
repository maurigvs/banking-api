package com.maurigvs.bank.accountapi.controller;

import com.maurigvs.bank.accountapi.util.Utils;
import com.maurigvs.bank.accountapi.dto.ConsumerRequest;
import com.maurigvs.bank.accountapi.dto.ErrorResponse;
import com.maurigvs.bank.accountapi.model.Consumer;
import com.maurigvs.bank.accountapi.service.ConsumerService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsumerController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ConsumerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ConsumerService service;

    private static final String URL_PATH = "/consumer";

    @Test
    void should_return_Created_when_post_consumer_account() throws Exception {
        var consumerRequest = new ConsumerRequest("63592564528", 123456);
        var jsonRequest = Utils.ofJson(consumerRequest);

        mvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        verify(service).create(any(Consumer.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void should_return_Bad_Request_when_MethodArgumentNotValidException_is_thrown() throws Exception {
        var consumerRequest = new ConsumerRequest("6359256452", 123456);
        var errorResponse = new ErrorResponse("Bad Request",
                "customerCpf size must be between 11 and 11");
        var jsonRequest = Utils.ofJson(consumerRequest);
        var jsonResponse = Utils.ofJson(errorResponse);

        mvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse));

        verifyNoInteractions(service);
    }
}