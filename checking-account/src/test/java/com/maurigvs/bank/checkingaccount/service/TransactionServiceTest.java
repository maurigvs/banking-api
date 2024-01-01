package com.maurigvs.bank.checkingaccount.service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {TransactionService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    @MockBean
    TransactionRepository transactionRepository;

    @Captor
    ArgumentCaptor<Transaction> transactionArgumentCaptor;

    @Test
    void should_make_credit_transaction_into_account() {

        var account = new Account(23456L, 345678L, 123456, 0.00);

        transactionService.credit(account, "Initial deposit", 100.00);

        then(transactionRepository).should(times(1)).save(transactionArgumentCaptor.capture());
        then(transactionRepository).shouldHaveNoMoreInteractions();
        var transaction = transactionArgumentCaptor.getValue();
        assertNull(transaction.getId());
        assertEquals("Initial deposit", transaction.getDescription());
        assertEquals(100.00, transaction.getAmount());
        assertEquals(account, transaction.getAccount());
    }
}