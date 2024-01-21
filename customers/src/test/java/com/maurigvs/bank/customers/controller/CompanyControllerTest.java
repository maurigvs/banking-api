package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.controller.dto.ExceptionDto;
import com.maurigvs.bank.customers.controller.dto.PostCompanyDto;
import com.maurigvs.bank.customers.exception.BusinessException;
import com.maurigvs.bank.customers.mock.Mocks;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CompanyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CompanyService companyService;

    @Test
    void should_return_created_when_post_company() throws Exception {

        var request = Mocks.ofCreateCompanyRequest();
        willDoNothing().given(companyService).createCompany(any(PostCompanyDto.class));

        mockMvc.perform(post("/customer/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isCreated());

        then(companyService).should().createCompany(eq(request));
        then(companyService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_BusinessException_is_thrown() throws Exception {

        var request = Mocks.ofCreateCompanyRequest();
        var message = "The company needs to be older than 6 months";
        var response = new ExceptionDto("Bad Request", message);
        willThrow(new BusinessException(message)).given(companyService).createCompany(any());

        mockMvc.perform(post("/customer/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response), true));

        then(companyService).should().createCompany(request);
        then(companyService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_MethodArgumentNotValidException_is_thrown() throws Exception {

        var request = new PostCompanyDto(null, null, null,
                null, null, null);

        var messages = List.of(
                "businessName is required",
                "email is required",
                "legalName is required",
                "phoneNumber is required",
                "startDate is required",
                "taxId is required");

        var response = new ExceptionDto("Bad Request", messages);

        mockMvc.perform(post("/customer/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response)));

        then(companyService).shouldHaveNoInteractions();
    }
}