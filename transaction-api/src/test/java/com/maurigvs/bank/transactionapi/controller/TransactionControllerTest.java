package com.maurigvs.bank.transactionapi.controller;

import com.maurigvs.bank.transactionapi.dto.ErrorResponse;
import com.maurigvs.bank.transactionapi.dto.TransactionRequest;
import com.maurigvs.bank.transactionapi.model.Transaction;
import com.maurigvs.bank.transactionapi.service.AccountService;
import com.maurigvs.bank.transactionapi.service.CustomerService;
import com.maurigvs.bank.transactionapi.service.TransactionService;
import com.maurigvs.bank.transactionapi.util.Utils;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;

    // TODO Update current tests
    @MockBean
    private CustomerService customerService;

    // TODO Update current tests
    @MockBean
    private AccountService accountService;

    private static final String URL_PATH = "/transaction";

    @Test
    void should_return_Created_when_post_consumer_account() throws Exception {
        var transactionRequest = new TransactionRequest(
                "63592564528",
                1L,
                "CREDIT",
                "Initial deposit",
                100.00);
        var jsonRequest = Utils.ofJson(transactionRequest);

        mvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());

        verify(transactionService).create(any(Transaction.class));
        verifyNoMoreInteractions(transactionService);
    }

    @Test
    void should_return_Bad_Request_when_MethodArgumentNotValidException_is_thrown() throws Exception {
        var transactionRequest = new TransactionRequest(
                "63592564528",
                1L,
                "",
                "Initial deposit",
                100.00);
        var errorResponse = new ErrorResponse(
                "Bad Request",
                "operation must not be blank");
        var jsonRequest = Utils.ofJson(transactionRequest);
        var jsonResponse = Utils.ofJson(errorResponse);

        mvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonResponse));

        verifyNoInteractions(transactionService);
    }
}