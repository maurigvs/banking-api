package com.maurigvs.bank.customers.service;

import com.maurigvs.bank.customers.model.dto.CompanyRequest;
import com.maurigvs.bank.customers.model.dto.PersonRequest;
import com.maurigvs.bank.customers.model.entity.Company;
import com.maurigvs.bank.customers.model.entity.ContactInfo;
import com.maurigvs.bank.customers.model.entity.Person;
import com.maurigvs.bank.customers.model.exception.BusinessException;
import com.maurigvs.bank.customers.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {CustomerService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Captor
    ArgumentCaptor<Person> personCaptor;

    @Captor
    ArgumentCaptor<Company> companyCaptor;

    @Test
    void should_save_person_when_create_from_request() throws BusinessException {

        var request = new PersonRequest(
                "30727104578",
                "John D.",
                "Snow",
                "28/07/1986",
                "john.snow@gmail.com",
                "973722818");

        var person = new Person(null,
                "30727104578",
                LocalDate.now(),
                true,
                new ContactInfo("john.snow@gmail.com", "973722818"),
                "John D.",
                "Snow",
                LocalDate.of(1986,7,28));

        customerService.createPerson(request);

        then(customerRepository).should(times(1)).save(personCaptor.capture());
        then(customerRepository).shouldHaveNoMoreInteractions();

        var result = personCaptor.getValue();
        assertNull(result.getId());
        assertEquals(request.taxId(), result.getTaxId());
        assertEquals(LocalDate.now(), result.getSince());
        assertTrue(result.getActive());
        assertEquals(request.name(), result.getName());
        assertEquals(request.surname(), result.getSurname());
        assertEquals(LocalDate.of(1986,7,28), result.getBirthDate());
        assertEquals(request.email(), result.getContactInfo().getEmailAddress());
        assertEquals(request.phone(), result.getContactInfo().getPhoneNumber());
        assertEquals(person, result);
    }

    @Test
    void should_throw_exception_if_person_age_is_not_elegible() {

        var request = new PersonRequest(
                "30727104578",
                "John D.",
                "Snow",
                "28/07/2023",
                "john.snow@gmail.com",
                "973722818");

        assertEquals("Person has age not elegible",
                assertThrows(BusinessException.class, () ->
                        customerService.createPerson(request)).getMessage());
    }

    @Test
    void should_throw_exception_if_birth_date_not_valid_when_create_person() {

        var request = new PersonRequest(
                "30727104578",
                "John D.",
                "Snow",
                "1986-07-28",
                "john.snow@gmail.com",
                "973722818");

        assertEquals("birthDate must be in the format dd/MM/yyyy",
                assertThrows(IllegalArgumentException.class, () ->
                        customerService.createPerson(request)).getMessage());
    }

    @Test
    void should_save_company_when_create_from_request() {

        var request = new CompanyRequest(
                "30727104578",
                "Apple",
                "Apple Computer Inc.",
                "01/07/1978",
                "admin@apple.com",
                "973722818");

        var company = new Company(null,
                "30727104578",
                LocalDate.now(),
                true,
                new ContactInfo("admin@apple.com", "973722818"),
                "Apple",
                "Apple Computer Inc.",
                LocalDate.of(1978,7,1));

        customerService.createCompany(request);

        then(customerRepository).should(times(1)).save(companyCaptor.capture());
        then(customerRepository).shouldHaveNoMoreInteractions();

        var result = companyCaptor.getValue();
        assertNull(result.getId());
        assertEquals(request.taxId(), result.getTaxId());
        assertEquals(LocalDate.now(), result.getSince());
        assertTrue(result.getActive());
        assertEquals(request.businessName(), result.getBusinessName());
        assertEquals(request.legalName(), result.getLegalName());
        assertEquals(LocalDate.of(1978,7,1), result.getStartDate());
        assertEquals(request.contactEmail(), result.getContactInfo().getEmailAddress());
        assertEquals(request.contactPhone(), result.getContactInfo().getPhoneNumber());
        assertEquals(company, result);
    }

}
