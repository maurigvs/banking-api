package br.com.maurigvs.banking.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.maurigvs.banking.controller.dto.AccountCreated;
import br.com.maurigvs.banking.controller.dto.AccountRequest;
import br.com.maurigvs.banking.controller.dto.AccountResponse;
import br.com.maurigvs.banking.exception.ErrorMessage;
import br.com.maurigvs.banking.mock.Mocks;
import br.com.maurigvs.banking.model.Account;
import br.com.maurigvs.banking.service.AccountService;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;
    
    @Test
    void should_ReturnOK_when_ListAccounts() throws Exception{
        // given
        List<Account> accountList = Mocks.accountList();
        List<AccountResponse> accountResponse = Mocks.accountListResponse(accountList);
        String listAsJson = Mocks.parseToJson(accountResponse);
        given(accountService.listAccounts()).willReturn(accountList);

        // when
        mockMvc.perform(get("/account/list"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(listAsJson));

        // then
        verify(accountService, times(1)).listAccounts();
        verifyNoMoreInteractions(accountService);
    }

    @Test
    void should_ReturnCreated_when_OpenAccount() throws Exception{
        // given
        Account account = Mocks.account();
        AccountRequest request = Mocks.accountRequest(account);
        String requestAsJson = Mocks.parseToJson(request);
        AccountCreated response = Mocks.accountCreated(account);
        String responseAsJson = Mocks.parseToJson(response);
        given(accountService.openAccount(anyString(), anyString(), anyString())).willReturn(account);

        // when
        mockMvc.perform(post("/account/open")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestAsJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(responseAsJson));
        
            // then
        verify(accountService, times(1))
            .openAccount(request.getTaxId(), request.getName(), request.getSurname());
        verifyNoMoreInteractions(accountService);
    }

    @Test
    void should_ReturnNotImplemented_when_DeleteAccount() throws Exception{
        // given
        ErrorMessage message = Mocks.mockErrorMessage(HttpStatus.NOT_IMPLEMENTED);
        String errorAsJson = Mocks.parseToJson(message);
        
        // when... then
        mockMvc.perform(delete("/account"))
            .andExpect(status().isNotImplemented())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(errorAsJson));
    }
}
