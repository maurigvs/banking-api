package br.com.maurigvs.banking.controller;

import static org.mockito.ArgumentMatchers.anyDouble;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.maurigvs.banking.model.dto.AccountCreated;
import br.com.maurigvs.banking.model.dto.AccountRequest;
import br.com.maurigvs.banking.model.dto.AccountResponse;
import br.com.maurigvs.banking.exception.BusinessException;
import br.com.maurigvs.banking.model.dto.ErrorMessage;
import br.com.maurigvs.banking.mock.Mocks;
import br.com.maurigvs.banking.model.entity.Account;
import br.com.maurigvs.banking.service.AccountService;
import br.com.maurigvs.banking.service.OperationsService;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @MockBean
    OperationsService operationsService;
    
    @Test
    void should_ReturnOK_when_ListAccounts() throws Exception {
        // given
        List<Account> accountList = Mocks.accountList();
        List<AccountResponse> accountResponse = Mocks.accountResponseList(accountList);
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
        AccountRequest request = Mocks.accountRequest();
        String requestAsJson = Mocks.parseToJson(request);
        Account account = Mocks.account();
        String responseAsJson = Mocks.parseToJson(new AccountCreated(account));
        given(operationsService.openAccount(anyString(), anyString(), anyString(), anyDouble()))
                .willReturn(account);

        // when
        mockMvc.perform(post("/account/open")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestAsJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(responseAsJson));
        
        // then
        verify(operationsService, times(1)).openAccount(request.getTaxId(),
                request.getName(), request.getSurname(), request.getInitialDeposit());
        verifyNoMoreInteractions(operationsService);
    }

    @Test
    void should_ReturnBadRequest_if_CustomerAlreadyExists_when_OpenAccount() throws Exception{
        // given
        String requestAsJson = Mocks.parseToJson(Mocks.accountRequest());
        String errorMessage = "Customer already exists";
        String errorAsJson = Mocks.parseToJson(new ErrorMessage(errorMessage));
        given(operationsService.openAccount(anyString(), anyString(), anyString(), anyDouble()))
            .willThrow(new BusinessException(errorMessage));

        // when... then
        mockMvc.perform(post("/account/open")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestAsJson))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(errorAsJson));
    }

    @Test
    void should_ReturnNotImplemented_when_DeleteAccount() throws Exception{
        // given
        ErrorMessage message = new ErrorMessage("No static resource account.");
        String errorAsJson = Mocks.parseToJson(message);
        
        // when... then
        mockMvc.perform(delete("/account"))
            .andExpect(status().isNotImplemented())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(errorAsJson));
    }
}
