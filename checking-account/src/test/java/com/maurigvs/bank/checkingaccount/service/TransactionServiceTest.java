package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.model.entity.Transaction;
import com.maurigvs.bank.checkingaccount.repository.TransactionRepository;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {TransactionServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TransactionServiceTest {

    @Autowired
    TransactionService service;

    @MockBean
    TransactionRepository repository;

    @Captor
    ArgumentCaptor<Transaction> captor;

    @Test
    void should_create_transaction_successfully() throws BusinessRuleException {

        var description = "Initial deposit";
        var amount = 150.00;
        var account = new Account(23456L, 345678L, 123456, 0.00);

        service.credit(account, description, amount);

        then(repository).should(times(1)).save(captor.capture());
        then(repository).shouldHaveNoMoreInteractions();

        var transaction = captor.getValue();
        assertNull(transaction.getId());
        assertEquals(description, transaction.getDescription());
        assertEquals(amount, transaction.getAmount());
        assertSame(account, transaction.getAccount());
    }

    @Test
    void should_throw_exception_when_credit_amount_is_not_positive() {

        var description = "Cash deposit";
        var amount = -150.00;
        var account = new Account(23456L, 345678L, 123456, 100.00);

        assertThatExceptionOfType(BusinessRuleException.class)
                .isThrownBy(() -> service.credit(account, description, amount))
                .withMessage("Transaction denied");

        then(repository).shouldHaveNoInteractions();
    }
}