package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.repository.AccountRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {AccountService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    TransactionService transactionService;

    @Captor
    ArgumentCaptor<Account> accountArgumentCaptor;

    @Test
    void should_open_account_successfully() throws BusinessRuleException {

        var request = new OpenAccountRequest(1L, 100.00,123456);
        given(accountRepository.save(any(Account.class))).willReturn(new Account(1L, 1L, 123456, 100.00));

        accountService.openAccount(request);

        then(accountRepository).should(times(1)).save(accountArgumentCaptor.capture());
        then(accountRepository).shouldHaveNoMoreInteractions();
        var account = accountArgumentCaptor.getValue();

        then(transactionService).should(times(1)).credit(account, "Initial deposit", request.initialDeposit());
        then(transactionService).shouldHaveNoMoreInteractions();

        assertNull(account.getId());
        assertEquals(request.accountHolderId(), account.getAccountHolderId());
        assertEquals(request.pinCode(), account.getPinCode());
        assertEquals(request.initialDeposit(), account.getBalance());
    }

    @Test
    void should_throw_exception_when_open_account_without_initial_deposit() {

        var request = new OpenAccountRequest(123L, 0.0, 123456);

        assertThatExceptionOfType(BusinessRuleException.class)
                .isThrownBy(() -> accountService.openAccount(request))
                .withMessage("A initial deposit is required to open account");

        then(accountRepository).shouldHaveNoInteractions();
    }
}