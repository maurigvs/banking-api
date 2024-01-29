package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.model.ConsumerAccount;
import com.maurigvs.bank.accounts.repository.ConsumerAccountRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.BDDMockito.then;

@SpringBootTest(classes = {ConsumerAccountService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ConsumerAccountServiceTest {

    @Autowired
    private ConsumerAccountService service;

    @MockBean
    private ConsumerAccountRepository repository;

    @Test
    void should_save_consumer_account_when_create() {

        var account = new ConsumerAccount(null, "86580512180",
                LocalDate.now(), 0.0, 123456);

        service.create(account);

        then(repository).should().save(account);
        then(repository).shouldHaveNoMoreInteractions();
    }
}