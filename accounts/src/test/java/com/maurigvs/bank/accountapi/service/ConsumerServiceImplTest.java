package com.maurigvs.bank.accountapi.service;

import com.maurigvs.bank.accountapi.model.Consumer;
import com.maurigvs.bank.accountapi.repository.ConsumerRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {ConsumerServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ConsumerServiceImplTest {

    @Autowired
    private ConsumerService service;

    @MockBean
    private ConsumerRepository repository;

    @Test
    void should_create_consumer_account() {
        var consumer = new Consumer(null, "63592564528", 123456);

        service.create(consumer);

        verify(repository, times(1)).save(consumer);
        verifyNoMoreInteractions(repository);
    }
}