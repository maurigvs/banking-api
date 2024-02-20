package com.maurigvs.bank.accountapi.controller;

import com.maurigvs.bank.accountapi.dto.CommercialRequest;
import com.maurigvs.bank.accountapi.model.Commercial;
import com.maurigvs.bank.accountapi.service.CommercialService;
import com.maurigvs.bank.accountapi.util.Utils;
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

@WebMvcTest(CommercialController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommercialControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommercialService service;

    private static final String URL_PATH = "/commercial";

    @Test
    void should_return_Created_when_post_commercial_account() throws Exception {
        var commercialRequest = new CommercialRequest("29382687000159", 123456);
        var jsonRequest = Utils.ofJson(commercialRequest);

        mvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        verify(service).create(any(Commercial.class));
        verifyNoMoreInteractions(service);
    }
}
