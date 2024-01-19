package com.maurigvs.bank.customers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maurigvs.bank.customers.model.dto.ApiErrorResponse;
import com.maurigvs.bank.customers.model.dto.CompanyRequest;
import com.maurigvs.bank.customers.model.dto.PersonRequest;
import com.maurigvs.bank.customers.model.exception.BusinessException;
import com.maurigvs.bank.customers.service.CustomerService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Test
    void should_return_created_when_post_person() throws Exception {

        var person = new PersonRequest(
                "30727104578",
                "John D.",
                "Snow",
                "28/07/1986",
                "john.snow@gmail.com",
                "973722818");
        var jsonRequest = parseToJson(person);

        mockMvc.perform(post("/customer/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        then(customerService).should(times(1)).createPerson(eq(person));
        then(customerService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_MethodArgumentNotValidException_is_thrown() throws Exception {

        var jsonRequest = "{}";
        var jsonResponse = parseToJson(new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                List.of("birthDate must not be blank",
                        "email must not be blank",
                        "name must not be blank",
                        "phone must not be blank",
                        "surname must not be blank",
                        "taxId must not be blank")));

        mockMvc.perform(post("/customer/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse, true));

        then(customerService).shouldHaveNoInteractions();
    }

    @Test
    void should_return_precondition_failed_when_BusinessException_is_thrown() throws Exception {

        var jsonRequest = parseToJson(new PersonRequest(
                "30727104578",
                "John D.",
                "Snow",
                "28/07/1986",
                "john.snow@gmail.com",
                "973722818"));

        var jsonResponse = parseToJson(new ApiErrorResponse(
                HttpStatus.PRECONDITION_FAILED.getReasonPhrase(),
                "Person has age not elegible"));

        willThrow(new BusinessException("Person has age not elegible"))
                .given(customerService).createPerson(any());

        mockMvc.perform(post("/customer/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isPreconditionFailed())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse, true));
    }

    @Test
    void should_return_bad_request_when_IllegalArgumentException_is_thrown() throws Exception {

        var jsonRequest = parseToJson(new PersonRequest(
                "30727104578",
                "John D.",
                "Snow",
                "28-07-1986",
                "john.snow@gmail.com",
                "973722818"));

        var jsonResponse = parseToJson(new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "birthDate must be in the format dd/MM/yyyy"));

        willThrow(new IllegalArgumentException("birthDate must be in the format dd/MM/yyyy"))
                .given(customerService).createPerson(any());

        mockMvc.perform(post("/customer/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse, true));
    }

    @Test
    void should_return_created_when_post_company() throws Exception {

        var company = new CompanyRequest(
                "30727104578",
                "Apple",
                "Apple Corporation Inc.",
                "01/07/1978",
                "admin@apple.com",
                "973722818");
        var jsonRequest = parseToJson(company);

        mockMvc.perform(post("/customer/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        then(customerService).should(times(1)).createCompany(eq(company));
        then(customerService).shouldHaveNoMoreInteractions();
    }

    public static String parseToJson(Object object) throws Exception {
        final var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om.writeValueAsString(object);
    }
}