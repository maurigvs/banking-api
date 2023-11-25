package br.com.maurigvs.banking.mock;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.maurigvs.banking.controller.AccountController.PostRequest;
import br.com.maurigvs.banking.controller.AccountController.PostResponse;
import br.com.maurigvs.banking.exception.ErrorMessage;
import br.com.maurigvs.banking.model.Account;
import br.com.maurigvs.banking.model.Customer;

public class Mocks {

    public static Account mockAccount(){
        return new Account(UUID.randomUUID(), LocalDate.now(), 
            new Customer("00950654906", "John", "Wayne", LocalDate.now()));
    }

    public static List<Account> getAccountsList() {
        return List.of(mockAccount(), mockAccount());
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

    public static PostRequest mockPostAccountRequest(Account account) {
        Customer customer = account.getCustomer();
        PostRequest request = new PostRequest();
        request.setTaxId(customer.getTaxId());
        request.setName(customer.getName());
        request.setSurname(customer.getSurname());
        return request;
    }

    public static PostResponse mockPostAccountResponse(Account account){
        return new PostResponse(account.getKeyCode().toString());
    }

    public static ErrorMessage mockErrorMessage(HttpStatus status) {
        return new ErrorMessage(
            status.value(), 
            status.getReasonPhrase(), 
            "No static resource account.");
    }
}