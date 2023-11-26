package br.com.maurigvs.banking.mock;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.maurigvs.banking.controller.dto.AccountCreated;
import br.com.maurigvs.banking.controller.dto.AccountRequest;
import br.com.maurigvs.banking.controller.dto.AccountResponse;
import br.com.maurigvs.banking.exception.ErrorMessage;
import br.com.maurigvs.banking.model.Account;
import br.com.maurigvs.banking.model.Customer;

public class Mocks {

    public static Account account(){
        return new Account(null, UUID.randomUUID(), LocalDate.now(), 
            new Customer(null, "96436322884", "John", "Wayne", LocalDate.now()));
    }

    public static List<Account> accountList(){
        return List.of(account(), account());
    }
    
    public static List<AccountResponse> accountListResponse(List<Account> accounts) {
        return accounts.stream().map(a -> new AccountResponse(a)).collect(Collectors.toList());
    }

    public static String parseToJson(Object object) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            return om.writeValueAsString(object);
            
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static AccountRequest accountRequest(Account account) {
        Customer customer = account.getCustomer();
        AccountRequest request = new AccountRequest();
        request.setTaxId(customer.getTaxId());
        request.setName(customer.getName());
        request.setSurname(customer.getSurname());
        return request;
    }

    public static AccountCreated accountCreated(Account account){
        return new AccountCreated(account);
    }

    public static ErrorMessage mockErrorMessage(HttpStatus status) {
        return new ErrorMessage(
            status.value(), 
            status.getReasonPhrase(), 
            "No static resource account.");
    }
}