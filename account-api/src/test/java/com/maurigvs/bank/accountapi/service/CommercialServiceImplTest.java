package com.maurigvs.bank.accountapi.service;

import com.maurigvs.bank.accountapi.model.Commercial;
import com.maurigvs.bank.accountapi.repository.CommercialRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {CommercialServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommercialServiceImplTest {

    @Autowired
    private CommercialService service;

    @MockBean
    private CommercialRepository repository;

    @Test
    void should_create_commercial_account() {
        var commercialAccount = new Commercial(null,
                "29382687000159",
                0.0,
                123456,
                LocalDate.now());

        service.create(commercialAccount);

        verify(repository, times(1)).save(commercialAccount);
        verifyNoMoreInteractions(repository);
    }
}
