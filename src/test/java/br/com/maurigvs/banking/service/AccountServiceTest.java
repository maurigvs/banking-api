package br.com.maurigvs.banking.service;

import br.com.maurigvs.banking.mock.Mocks;
import br.com.maurigvs.banking.model.entity.Account;
import br.com.maurigvs.banking.model.entity.Customer;
import br.com.maurigvs.banking.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {AccountService.class})
class AccountServiceTest {

    @Autowired
    AccountService service;

    @MockBean
    AccountRepository repository;

    @Test
    void should_CreateAccount_when_requested() {
        // given
        Customer customer = Mocks.customer();
        Account account = Mocks.account(customer);
        UUID keyCode = account.getKeyCode();
        given(repository.save(any())).willReturn(account);
        
        // when
        Account result = service.create(customer);
        
        // then
        verify(repository, times(1)).save(any());
        verifyNoMoreInteractions(repository);

        assertThat(result.getId()).isEqualTo(123L);
        assertThat(result.getKeyCode()).isEqualTo(keyCode);
        assertThat(result.getOpenedAt()).isEqualTo(LocalDate.now());
        assertThat(result.getCustomer().getTaxId()).isEqualTo(customer.getTaxId());
        assertThat(result.getBalance()).isEqualTo(0.00);
        assertThat(result.getTransactions()).isEmpty();
    }

    @Test
    void should_UpdateBalance_when_requested() {
        // given
        Long accountId = 123L;
        Account account = Mocks.account();
        given(repository.getReferenceById(accountId)).willReturn(account);

        // when
        service.updateBalance(accountId, 150.00);

        // then
        verify(repository, times(1)).getReferenceById(accountId);
        verify(repository, times(1)).save(account);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_ListAccounts_when_requested() {
        // given
        List<Account> accounts = Mocks.accountList();
        given(repository.findAll()).willReturn(accounts);

        // when
        List<Account> result = service.listAccounts();

        // then
        verify(repository, times(1)).findAll();
        assertThat(result).isEqualTo(accounts);
    }
}