package com.maurigvs.bank.accounts.service;

import com.maurigvs.bank.accounts.model.Operator;
import com.maurigvs.bank.accounts.model.CommercialAccount;
import com.maurigvs.bank.accounts.repository.CommercialAccountRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.BDDMockito.then;

@SpringBootTest(classes = {CommercialAccountService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommercialAccountServiceTest {

    @Autowired
    private CommercialAccountService service;

    @MockBean
    private CommercialAccountRepository repository;

    @Test
    void should_save_commercial_account_when_create() {

        var account = new CommercialAccount(null, "72097237000143",
                LocalDate.now(), 0.0, 123456);

        var operator = new Operator(null,"John Snow", "john@snow.com",
                "932567436", 234567, account);
        account.getOperators().add(operator);

        service.create(account);

        then(repository).should().save(account);
        then(repository).shouldHaveNoMoreInteractions();
    }
}