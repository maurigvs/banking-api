package br.com.maurigvs.banking.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.maurigvs.banking.repository.AccountRepository;
import br.com.maurigvs.banking.repository.CustomerRepository;
import br.com.maurigvs.banking.mock.Mocks;
import br.com.maurigvs.banking.model.Account;

@SpringBootTest(classes = {AccountService.class})
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    AccountRepository accountRepository;

    @Test
    void should_ListAccounts_when_requested() {
        // given
        List<Account> accounts = Mocks.accountList();
        given(accountRepository.findAll()).willReturn(accounts);
        // when
        List<Account> result = accountService.listAccounts();
        // then
        verify(accountRepository, times(1)).findAll();
        assertEquals(accounts, result);
    }

    @Test
    void should_CreateCustomerAndAccount_when_requested() {
        // given
        Account account = Mocks.account();
        UUID keyCode = account.getKeyCode();
        given(accountRepository.save(any())).willReturn(account);
        // when
        Account result = accountService.openAccount("96436322884", "John", "Wayne");
        // then
        assertAll(
            () -> verify(customerRepository, times(1)).save(any()),
            () -> verify(accountRepository, times(1)).save(any()),
            () -> assertEquals(keyCode, result.getKeyCode()),
            () -> assertEquals(LocalDate.now(), result.getOpenedAt()),
            () -> assertEquals("96436322884", result.getCustomer().getTaxId()),
            () -> assertEquals("John", result.getCustomer().getName()),
            () -> assertEquals("Wayne", result.getCustomer().getSurname()),
            () -> assertEquals(LocalDate.now(), result.getCustomer().getSince())
        );
    }
}
