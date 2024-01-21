package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.mock.Mocks;
import com.maurigvs.bank.customers.controller.dto.PersonRequest;
import com.maurigvs.bank.customers.controller.dto.ExceptionResponse;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.service.PersonService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
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
    PersonService personService;

    @Test
    void should_return_created_when_post_person_request() throws Exception {

        var request = new PersonRequest("86580512180", "John", "Wayne",
                "28/07/1988", "john@wayne.com", "+5511984833929");

        willDoNothing().given(personService).createPerson(any(PersonRequest.class));

        mockMvc.perform(post("/customer/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isCreated());

        then(personService).should().createPerson(eq(request));
        then(personService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_BusinessException_is_thrown() throws Exception {

        var request = new PersonRequest("86580512180", "John", "Wayne",
                "28/07/1988", "john@wayne.com", "+5511984833929");

        var message = "The account holder already exists";
        var response = new ExceptionResponse("Bad Request", message);

        willThrow(new BusinessException(message))
                .given(personService).createPerson(any(PersonRequest.class));

        mockMvc.perform(post("/customer/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response)));

        then(personService).should().createPerson(request);
        then(personService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_MethodArgumentNotValidException_is_thrown() throws Exception {

        var request = new PersonRequest(null, null, null, null, null, null);

        var messages = List.of(
                "birthDate is required",
                "email is required",
                "name is required",
                "phoneNumber is required",
                "surname is required",
                "taxId is required");

        var response = new ExceptionResponse("Bad Request", messages);

        mockMvc.perform(post("/customer/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response)));

        then(personService).shouldHaveNoInteractions();
    }
}