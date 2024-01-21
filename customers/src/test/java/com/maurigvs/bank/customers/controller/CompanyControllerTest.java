package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.controller.dto.CreateCompanyRequest;
import com.maurigvs.bank.customers.controller.dto.ErrorResponse;
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
    void should_return_created_when_post_company_request() throws Exception {

        var request = Mocks.ofCreateCompanyRequest();
        willDoNothing().given(companyService).createCompany(any(CreateCompanyRequest.class));

        mockMvc.perform(post("/account-holder/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isCreated());

        then(companyService).should().createCompany(eq(request));
        then(companyService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_company_validation_fails() throws Exception {

        var request = new CreateCompanyRequest(null, null, null,
                null, null, null);

        var messages = List.of(
                "The business name is required",
                "The cnpj is required",
                "The contact email address is required",
                "The contact phone number is required",
                "The legal name is required",
                "The opening date is required");

        assertBadRequestWhenPostCompany(request, messages);
    }

    @Test
    void should_return_bad_request_when_cnpj_validation_fails() throws Exception {

        var request = new CreateCompanyRequest("Apple Inc.", "Apple", "01/07/1980",
                "12345", "contact@apple.com", "+551199882211");

        var messages = List.of("The cnpj must have 14 numbers without any other characters");

        assertBadRequestWhenPostCompany(request, messages);
    }

    @Test
    void should_return_bad_request_when_contact_email_validation_fails() throws Exception {

        var request = new CreateCompanyRequest("Apple Inc.", "Apple", "01/07/1980",
                "72097237000143", "emailtypedwrong.com", "+551199882211");

        var messages = List.of("The contact email must be an valid address");

        assertBadRequestWhenPostCompany(request, messages);
    }

    void assertBadRequestWhenPostCompany(CreateCompanyRequest request, List<String> messages) throws Exception {

        var response = new ErrorResponse("Bad Request", messages);

        mockMvc.perform(post("/account-holder/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.ofJsonFrom(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.ofJsonFrom(response)));

        then(companyService).shouldHaveNoInteractions();
    }
}