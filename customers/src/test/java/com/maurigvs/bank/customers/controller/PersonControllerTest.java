package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.mock.Mocks;
import com.maurigvs.bank.customers.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.customers.controller.dto.ErrorResponse;
import com.maurigvs.bank.customers.exception.BusinessRuleException;
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

        var request = Mocks.ofCreatePersonRequest();
        willDoNothing().given(personService).createPerson(any(CreatePersonRequest.class));

        mockMvc.perform(post("/account-holder/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isCreated());

        then(personService).should().createPerson(eq(request));
        then(personService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_exception_is_thrown() throws Exception {

        var request = Mocks.ofCreatePersonRequest();
        var response = new ErrorResponse("Bad Request","The account holder already exists");

        willThrow(new BusinessRuleException("The account holder already exists"))
                .given(personService).createPerson(any(CreatePersonRequest.class));

        mockMvc.perform(post("/account-holder/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response)));
    }

    @Test
    void should_return_bad_request_when_person_validation_fails() throws Exception {

        var request = new CreatePersonRequest(null, null, null,
                null, null, null);

        var messages = List.of(
                "The birth date is required",
                "The cpf is required",
                "The email address is required",
                "The name is required",
                "The phone number is required",
                "The surname is required");

        assertBadRequestWhenPostPerson(request, messages);
    }

    @Test
    void should_return_bad_request_when_cpf_validation_fails() throws Exception {

        var request = new CreatePersonRequest("John", "Mayer", "23/05/1983",
                "123456", "john@mayer.com", "+1345678234");

        var messages = List.of("The cpf must have 11 numbers without any other characters");

        assertBadRequestWhenPostPerson(request, messages);
    }

    @Test
    void should_return_bad_request_when_email_validation_fails() throws Exception {

        var request = new CreatePersonRequest("John", "Mayer", "23/05/1983",
                "123456", "john@mayer.com", "+1345678234");

        var messages = List.of("The cpf must have 11 numbers without any other characters");

        assertBadRequestWhenPostPerson(request, messages);
    }

    void assertBadRequestWhenPostPerson(CreatePersonRequest request, List<String> messages) throws Exception {

        var response = new ErrorResponse("Bad Request", messages);

        mockMvc.perform(post("/account-holder/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response)));

        then(personService).shouldHaveNoInteractions();
    }

}