package com.maurigvs.bank.checkingaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maurigvs.bank.checkingaccount.exception.AuthenticationException;
import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.ErrorResponse;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.service.BankService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
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
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BankControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BankService service;

    @Nested
    @DisplayName("/account/open")
    class OpenAccountTests {

        @Test
        void should_return_created_when_post_account() throws Exception {

            var request = new OpenAccountRequest(123L, 100.00, 123456);
            willDoNothing().given(service).openAccount(any(OpenAccountRequest.class));

            mockMvc.perform(post("/account/open")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ofJsonFrom(request)))
                    .andExpect(status().isCreated());

            then(service).should(times(1)).openAccount(eq(request));
            then(service).shouldHaveNoMoreInteractions();
        }

        @Test
        void should_return_bad_request_when_post_account_not_elegible() throws Exception {

            var request = new OpenAccountRequest(123L, 0.00, 123456);
            var response = new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    "A initial deposit is required to open account");

            willThrow(new BusinessRuleException("A initial deposit is required to open account"))
                    .given(service).openAccount(any(OpenAccountRequest.class));

            mockMvc.perform(post("/account/open")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ofJsonFrom(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(ofJsonFrom(response)));

            then(service).should(times(1)).openAccount(eq(request));
            then(service).shouldHaveNoMoreInteractions();
        }

        @Test
        void should_return_bad_request_when_open_account_validations_fails() throws Exception {

            var request = new OpenAccountRequest(null, null, null);

            var messagesExpected = List.of("The account holder id is required",
                    "The initial deposit is required", "The pin code is required");

            assertBadRequestWhenPostAccount(request, messagesExpected);
        }

        @Test
        void should_return_bad_request_when_pin_code_validation_fails() throws Exception {

            var request = new OpenAccountRequest(1L, 100.00, 99999);

            var messagesExpected = List.of("The pin code must have 6 digits.");

            assertBadRequestWhenPostAccount(request, messagesExpected);
        }

        void assertBadRequestWhenPostAccount(OpenAccountRequest request, List<String> messages) throws Exception {

            var response = new ErrorResponse("Bad Request", messages);

            mockMvc.perform(post("/account/open")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ofJsonFrom(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(ofJsonFrom(response)));

            then(service).shouldHaveNoInteractions();
        }
    }

    @Nested
    @DisplayName("/account/deposit")
    class DepositTests {

        @Test
        void should_return_ok_when_post_deposit() throws Exception {

            var accoundId = 234567L;
            var pinCode = 123456;
            var amount = 250.30;
            willDoNothing().given(service).makeDeposit(anyLong(), anyInt(), anyDouble());

            mockMvc.perform(post("/account/234567/123456/deposit/250.30"))
                    .andExpect(status().isAccepted());

            then(service).should(times(1)).makeDeposit(accoundId, pinCode, amount);
            then(service).shouldHaveNoMoreInteractions();
        }

        @Test
        void should_return_forbidden_when_post_deposit_with_wrong_pincode() throws Exception {

            var response = new ErrorResponse(HttpStatus.FORBIDDEN.getReasonPhrase(), "Failed");
            willThrow(new AuthenticationException("Failed")).given(service).makeDeposit(anyLong(), anyInt(), anyDouble());

            mockMvc.perform(post("/account/234567/123456/deposit/250.30"))
                    .andExpect(status().isForbidden())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(ofJsonFrom(response)));

            then(service).should().makeDeposit(234567L, 123456, 250.30);
            then(service).shouldHaveNoMoreInteractions();
        }
    }


    private static String ofJsonFrom(Object object) throws Exception {
        final var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om.writeValueAsString(object);
    }
}