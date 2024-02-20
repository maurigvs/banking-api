package com.maurigvs.bank.customerapi.controller;

import com.maurigvs.bank.customerapi.dto.ErrorResponse;
import com.maurigvs.bank.customerapi.dto.PersonRequest;
import com.maurigvs.bank.customerapi.service.PersonService;
import com.maurigvs.bank.customerapi.util.Utils;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@AutoConfigureWebMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService service;

    private static final String URL_PATH = "/person";

    @Test
    void should_return_Created_when_post_person_successfully() throws Exception {
        var personRequest = new PersonRequest(
                "John",
                "Snow",
                "63592564528",
                "28/07/1988",
                "john.snow@gmail.com",
                "+351654358130");

        var jsonRequest = Utils.ofJson(personRequest);

        mvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        verify(service, times(1)).create(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    void should_return_Bad_Request_when_DateFormatException_is_thrown() throws Exception {
        var personRequest = new PersonRequest(
                "John",
                "Snow",
                "63592564528",
                "28-07-88",
                "john.snow@gmail.com",
                "+351654358130");

        var errorResponse = new ErrorResponse(
                "Bad Request",
                "Date must be in the format: dd/MM/yyyy");

        var jsonRequest = Utils.ofJson(personRequest);
        var jsonResponse = Utils.ofJson(errorResponse);

        mvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse));

        verifyNoInteractions(service);
    }

    @Test
    void should_return_Bad_Request_when_MethodArgumentNotValidException_is_thrown() throws Exception {
        var personRequest = new PersonRequest(
                "John",
                "Snow",
                "63592564528",
                "28/07/1988",
                "john.snow@.com",
                "+351654358130");

        var errorResponse = new ErrorResponse(
                "Bad Request",
                "emailAddress must be a well-formed email address");

        var jsonRequest = Utils.ofJson(personRequest);
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
