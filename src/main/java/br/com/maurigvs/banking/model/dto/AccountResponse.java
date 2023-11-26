package br.com.maurigvs.banking.model.dto;

import br.com.maurigvs.banking.model.entity.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AccountResponse {

    private final String accountId;

    private final CustomerResponse customer;

    private final List<TransactionResponse> transactions = new ArrayList<>();

    private final Double balance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate openedAt;

    public AccountResponse(Account a) {
        this.accountId = a.getKeyCode().toString();
        this.customer = new CustomerResponse(a.getCustomer());
        this.transactions.addAll(a.getTransactions().stream().map(TransactionResponse::new).toList());
        this.balance = a.getBalance();
        this.openedAt = a.getOpenedAt();
    }
}