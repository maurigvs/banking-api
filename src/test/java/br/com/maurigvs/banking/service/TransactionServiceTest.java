package br.com.maurigvs.banking.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import br.com.maurigvs.banking.mock.Mocks;
import br.com.maurigvs.banking.model.entity.Transaction;
import br.com.maurigvs.banking.repository.TransactionRepository;

@SpringBootTest(classes = {TransactionService.class})
class TransactionServiceTest {

    @Autowired
    TransactionService service;

    @MockBean
    TransactionRepository repository;

    @Test
    void should_CreateTransaction_when_requested(){
        // given
        Transaction transaction = Mocks.transaction();
        given(repository.save(any())).willReturn(transaction);

        // when
        Transaction result = service.create("Initial deposit", 150.00, Mocks.account());

        // then
        verify(repository, times(1)).save(any());
        verifyNoMoreInteractions(repository);

        assertThat(result.getId()).isEqualTo(345L);
        assertThat(result.getDateTime()).isEqualTo(transaction.getDateTime());
        assertThat(result.getDescription()).isEqualTo("Initial deposit");
        assertThat(result.getAmount()).isEqualTo(150.00);
        assertThat(result.getAccount().getKeyCode()).isEqualTo(transaction.getAccount().getKeyCode());
    }
}
