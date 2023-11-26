package br.com.maurigvs.banking.mock;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.maurigvs.banking.model.dto.AccountRequest;
import br.com.maurigvs.banking.model.dto.AccountResponse;
import br.com.maurigvs.banking.model.entity.Account;
import br.com.maurigvs.banking.model.entity.Customer;
import br.com.maurigvs.banking.model.entity.Transaction;

public class Mocks {

    public static AccountRequest accountRequest(){
        AccountRequest request = new AccountRequest();
        request.setTaxId("96436322884");
        request.setName("John");
        request.setSurname("Wayne");
        request.setInitialDeposit(150.00);
        return request;
    }

    public static Account account(Customer customer){
        return new Account(123L, UUID.randomUUID(),0.00, customer);
    }

    public static Account account(){
        AccountRequest request = accountRequest();
        Account account = account(customer(request));
        account.getTransactions().add(transaction(request, account));
        return account;
    }

    public static Customer customer(AccountRequest request){
        return new Customer(234L, request.getTaxId(), request.getName(), request.getSurname());
    }

    public static Customer customer(){
        return customer(accountRequest());
    }

    public static Transaction transaction(AccountRequest request, Account account){
        return new Transaction(345L,"Initial deposit", request.getInitialDeposit(), account);
    }

    public static Transaction transaction(){
        AccountRequest request = accountRequest();
        return transaction(request, account(customer(request)));
    }

    public static List<Account> accountList(){
        return List.of(account(),account());
    }

    public static List<AccountResponse> accountResponseList(List<Account> accountList) {
        return accountList.stream().map(AccountResponse::new).collect(Collectors.toList());
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
}