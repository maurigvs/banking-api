package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.AuthenticationException;
import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(classes = {AccountServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AccountServiceImplTest {

    @Autowired
    AccountService service;

    @MockBean
    AccountRepository repository;

    @Captor
    ArgumentCaptor<Account> captor;

    @Nested
    @DisplayName("account main tests")
    class MainTests {

        @Test
        void should_open_account() {

            var request = new OpenAccountRequest(1L, 100.00,123456);
            given(repository.save(any(Account.class))).willReturn(new Account(1L, 1L, 123456, 100.00));

            service.openAccount(request);

            then(repository).should().save(captor.capture());
            then(repository).shouldHaveNoMoreInteractions();
            var account = captor.getValue();

            assertThat(account.getId()).isNull();
            assertThat(account.getAccountHolderId()).isEqualTo(request.accountHolderId());
            assertThat(account.getPinCode()).isEqualTo(request.pinCode());
            assertThat(account.getBalance()).isZero();
            assertThat(account.getTransactions()).isEmpty();
        }

        @Test
        void should_update_balance_when_credit_amount() {

            var account = new Account(123L, 456L, 987654, 3465.12);
            var amount = 1200.00;
            given(repository.getReferenceById(anyLong())).willReturn(account);

            service.creditAmount(account, amount);

            then(repository).should().getReferenceById(123L);
            then(repository).shouldHaveNoMoreInteractions();

            assertThat(account.getBalance()).isEqualTo(4665.12);
        }

        @Test
        void should_update_balance_when_debit_amount() {

            var account = new Account(123L, 456L, 987654, 2400.00);
            var amount = 720.00;
            given(repository.getReferenceById(anyLong())).willReturn(account);

            service.debitAmount(account, amount);

            then(repository).should().getReferenceById(123L);
            then(repository).shouldHaveNoMoreInteractions();

            assertThat(account.getBalance()).isEqualTo(1680.00);
        }
    }

    @Nested
    @DisplayName("account authentication tests")
    class AuthenticateTests {

        @Test
        void should_return_account_when_authenticate() throws AuthenticationException {

            var accountId = 123L;
            var pinCode = 987654;
            var account = new Account(accountId, 456L, pinCode, 2430.00);
            given(repository.findById(anyLong())).willReturn(Optional.of(account));

            var result = service.authenticate(accountId, pinCode);

            then(repository).should().findById(accountId);
            then(repository).shouldHaveNoMoreInteractions();
            assertThat(result).isSameAs(account);
        }

        @Test
        void should_throw_exception_when_account_not_found() {

            given(repository.findById(anyLong())).willReturn(Optional.empty());

            assertThatExceptionOfType(AuthenticationException.class)
                    .isThrownBy(() -> service.authenticate(123456L, 234567))
                    .withMessage("Account not found");

            then(repository).should().findById(123456L);
            then(repository).shouldHaveNoMoreInteractions();
        }

        @Test
        void should_throw_exception_when_pincode_doest_not_match() {

            var account = new Account(123L, 456L, 987654, 3465.12);
            given(repository.findById(anyLong())).willReturn(Optional.of(account));

            assertThatExceptionOfType(AuthenticationException.class)
                    .isThrownBy(() -> service.authenticate(123L, 234567))
                    .withMessage("Authentication failed");

            then(repository).should().findById(123L);
            then(repository).shouldHaveNoMoreInteractions();
        }
    }

    @Nested
    @DisplayName("account elegibility tests")
    class CheckElegibilityTests{

        @Test
        void should_check_elegibility() {

            var request = new OpenAccountRequest(123L, 100.0, 123456);

            assertDoesNotThrow(() -> service.checkElegibility(request));
        }

        @Test
        void should_throw_exception_when_open_account_not_elegible() {

            var request = new OpenAccountRequest(123L, 0.0, 123456);

            assertThatExceptionOfType(BusinessRuleException.class)
                    .isThrownBy(() -> service.checkElegibility(request))
                    .withMessage("A initial deposit is required to open account");
        }
    }
}