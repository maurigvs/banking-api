package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.dto.CompanyRequest;
import com.maurigvs.bank.customers.dto.ExceptionResponse;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.mock.Mocks;
import com.maurigvs.bank.customers.model.Company;
import com.maurigvs.bank.customers.service.CompanyService;
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
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    void should_return_created_when_post_company() throws Exception {

        var request = new CompanyRequest("72097237000143",
                "Company Services", "Company Services Ltd.",
                "03/05/2013", "john@wayne.com", "+5511984833929");

        mockMvc.perform(post("/customer/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isCreated());

        then(companyService).should().create(any(Company.class));
        then(companyService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_BusinessException_is_thrown() throws Exception {

        var request = new CompanyRequest("72097237000143",
                "Company Services", "Company Services Ltd.",
                "03/05/2013", "john@wayne.com", "+5511984833929");

        var message = "business exception message";
        var response = new ExceptionResponse("Bad Request", message);

        willThrow(new BusinessException(message)).given(companyService).create(any());

        mockMvc.perform(post("/customer/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response), true));

        then(companyService).should().create(any(Company.class));
        then(companyService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_MethodArgumentNotValidException_is_thrown() throws Exception {

        var request = new CompanyRequest(
                null, null, null,
                null, null, null);

        var messages = List.of(
                "businessName is required",
                "email is required",
                "legalName is required",
                "phoneNumber is required",
                "startDate is required",
                "taxId is required");

        var response = new ExceptionResponse("Bad Request", messages);

        mockMvc.perform(post("/customer/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response)));

        then(companyService).shouldHaveNoInteractions();
    }
}