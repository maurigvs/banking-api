package com.maurigvs.bank.accountholder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maurigvs.bank.accountholder.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.accountholder.controller.dto.ErrorResponse;
import com.maurigvs.bank.accountholder.exception.BusinessRuleException;
import com.maurigvs.bank.accountholder.service.PersonService;
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
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService service;

    @Test
    void should_return_created_when_post_person_request() throws Exception {

        var request = new CreatePersonRequest("John", "Wayne", "28/08/1988",
                "86580512180", "john@wayne.com", "+5511984833929");

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStringFrom(request)))
                .andExpect(status().isCreated());

        then(service).should(times(1)).createPerson(eq(request));
        then(service).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_if_exception_is_thrown_when_post_person() throws Exception {

        var request = new CreatePersonRequest("John", "Wayne", "1988-08-28",
                "86580512180", "john@wayne.com", "+5511984833929");

        var response = new ErrorResponse("Bad Request",
                "The date must have the format: dd/MM/yyyy");

        willThrow(new BusinessRuleException("The date must have the format: dd/MM/yyyy"))
                .given(service).createPerson(any(CreatePersonRequest.class));

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStringFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonStringFrom(response)));

        then(service).should(times(1)).createPerson(eq(request));
        then(service).shouldHaveNoMoreInteractions();
    }

    private String jsonStringFrom(Object object) throws Exception {
        final var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om.writeValueAsString(object);
    }
}