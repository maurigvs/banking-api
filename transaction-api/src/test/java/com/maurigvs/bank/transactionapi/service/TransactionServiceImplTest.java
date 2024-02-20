package com.maurigvs.bank.transactionapi.service;

import com.maurigvs.bank.transactionapi.model.Transaction;
import com.maurigvs.bank.transactionapi.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZonedDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {TransactionServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TransactionServiceImplTest {

    @Autowired
    private TransactionService service;

    @MockBean
    private TransactionRepository repository;

    @Test
    void should_create_transaction() {
        var transaction = new Transaction(null,
                "63592564528",
                1L,
                "CREDIT",
                "Initial deposit",
                100.00,
                ZonedDateTime.now());

        service.create(transaction);

        verify(repository, times(1)).save(transaction);
        verifyNoMoreInteractions(repository);
    }
}