package br.com.maurigvs.banking.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.maurigvs.banking.exception.BusinessException;
import br.com.maurigvs.banking.mock.Mocks;
import br.com.maurigvs.banking.model.entity.Account;
import br.com.maurigvs.banking.model.entity.Customer;
import br.com.maurigvs.banking.model.entity.Transaction;

@SpringBootTest(classes = {OperationsService.class})
class OperationsServiceTest {

    @Autowired
    OperationsService operationsService;

    @MockBean
    CustomerService customerService;

    @MockBean
    AccountService accountService;

    @MockBean
    TransactionService transactionService;

    @Test
    void should_OpenAccountWithBalance_when_requested() throws BusinessException {
        // given
        Customer customer = Mocks.customer();
        Account account = Mocks.account();
        Transaction transaction = Mocks.transaction();
        given(customerService.create(anyString(), anyString(), anyString())).willReturn(customer);
        given(accountService.create(any())).willReturn(account);
        given(transactionService.create(anyString(), anyDouble(), any())).willReturn(transaction);
        willDoNothing().given(accountService).updateBalance(any(), anyDouble());

        // when
        Account result = operationsService.openAccount("12345", "John", "Wayne", 150.00);

        // then
        assertThat(result.getKeyCode()).isEqualTo(account.getKeyCode());
        verify(customerService, times(1)).create(anyString(), anyString(), anyString());
        verify(accountService, times(1)).create(any());
        verify(transactionService, times(1)).create(anyString(), anyDouble(), any());
        verify(accountService, times(1)).updateBalance(any(), anyDouble());
        verifyNoMoreInteractions(customerService, accountService, transactionService);
    }
}