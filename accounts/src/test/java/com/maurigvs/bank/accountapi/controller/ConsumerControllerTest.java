package com.maurigvs.bank.accountapi.controller;

import com.maurigvs.bank.accountapi.controller.util.Utils;
import com.maurigvs.bank.accountapi.dto.ConsumerRequest;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}