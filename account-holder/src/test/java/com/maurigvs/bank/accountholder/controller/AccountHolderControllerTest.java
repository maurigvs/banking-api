package com.maurigvs.bank.accountholder.controller;

import com.maurigvs.bank.accountholder.Mocks;
import com.maurigvs.bank.accountholder.controller.dto.CreateCompanyRequest;
import com.maurigvs.bank.accountholder.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.accountholder.controller.dto.ErrorResponse;
import com.maurigvs.bank.accountholder.exception.BusinessRuleException;
import com.maurigvs.bank.accountholder.service.CompanyService;
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
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountHolderController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountHolderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CompanyService companyService;

    @MockBean
    PersonService personService;

    @Test
    void should_return_created_when_post_company_request() throws Exception {

        var request = Mocks.createCompanyRequest();
        willDoNothing().given(companyService).createCompany(any(CreateCompanyRequest.class));

        mockMvc.perform(post("/account-holder/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.jsonCompanyRequest()))
                .andExpect(status().isCreated());

        then(companyService).should(times(1)).createCompany(eq(request));
        then(companyService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_created_when_post_person_request() throws Exception {

        var request = Mocks.createPersonRequest();
        willDoNothing().given(personService).createPerson(any(CreatePersonRequest.class));

        mockMvc.perform(post("/account-holder/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.jsonPersonRequest()))
                .andExpect(status().isCreated());

        then(personService).should(times(1)).createPerson(eq(request));
        then(personService).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_bad_request_when_exception_is_thrown() throws Exception {

        var response = new ErrorResponse("Bad Request","The account holder already exists");
        willThrow(new BusinessRuleException("The account holder already exists"))
                .given(personService).createPerson(any(CreatePersonRequest.class));

        mockMvc.perform(post("/account-holder/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mocks.jsonPersonRequest()))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mocks.jsonStringFrom(response)));
    }
}