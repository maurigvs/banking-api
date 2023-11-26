package br.com.maurigvs.banking.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.maurigvs.banking.exception.BusinessException;
import br.com.maurigvs.banking.mock.Mocks;
import br.com.maurigvs.banking.model.entity.Customer;
import br.com.maurigvs.banking.repository.CustomerRepository;

@SpringBootTest(classes = {CustomerService.class})
class CustomerServiceTest {

    @Autowired
    CustomerService service;

    @MockBean
    CustomerRepository repository;

    @Test
    void should_CreateCustomer_when_requested() throws BusinessException {
        // given
        Customer customer = Mocks.customer();
        given(repository.existsByTaxId(anyString())).willReturn(false);
        given(repository.save(any())).willReturn(customer);

        // when
        Customer result = service.create("96436322884", "John", "Wayne");

        // then
        verify(repository, times(1)).existsByTaxId(anyString());
        verify(repository, times(1)).save(any());
        verifyNoMoreInteractions(repository);

        assertThat(result.getId()).isEqualTo(234L);
        assertThat(result.getTaxId()).isEqualTo("96436322884");
        assertThat(result.getName()).isEqualTo("John");
        assertThat(result.getSurname()).isEqualTo("Wayne");
        assertThat(result.getSince()).isEqualTo(LocalDate.now());
        assertThat(result.getAccounts()).isEmpty();
    }

        @Test
    void should_ThrowBusinessException_when_CustomerAlreadyExistsByTaxId(){
        // given
        given(repository.existsByTaxId(anyString())).willReturn(true);

        // when... then
        assertThatExceptionOfType(BusinessException.class)
            .isThrownBy(() -> service.create("96436322884", "Maria", "Callas"))
            .withMessage("Customer already exists");
    }
}