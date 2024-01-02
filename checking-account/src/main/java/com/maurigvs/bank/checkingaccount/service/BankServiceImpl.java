package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.AuthenticationException;
import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.OpenAccountRequest;
import com.maurigvs.bank.checkingaccount.model.dto.StatementResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final AccountService accounts;
    private final TransactionService transactions;

    @Transactional
    @Override
    public void openAccount(OpenAccountRequest request) throws BusinessRuleException {
        accounts.checkElegibility(request);
        var account = accounts.openAccount(request);
        transactions.credit(account, "Initial deposit", request.initialDeposit());
        accounts.creditAmount(account, request.initialDeposit());
    }

    @Transactional
    @Override
    public void makeDeposit(Long accountId, Integer pinCode, Double amount) throws BusinessRuleException {
        var account = accounts.authenticate(accountId, pinCode);
        transactions.credit(account, "Cash Deposit", amount);
        accounts.creditAmount(account, amount);
    }

    @Transactional
    @Override
    public void makeWithdraw(Long accountId, Integer pinCode, Double amount) throws BusinessRuleException {
        var account = accounts.authenticate(accountId, pinCode);
        transactions.debit(account, "ATM Withdraw", amount);
        accounts.debitAmount(account, amount);
    }

    //TODO: Implement unit test
    @Override
    public StatementResponse getStatement(Long accountId, Integer pinCode) throws AuthenticationException {
        var account = accounts.authenticate(accountId, pinCode);
        var dateTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        var statementItens = transactions.parseToStatementItensList(account.getTransactions());
        return new StatementResponse(account.getId(), dateTime, statementItens, account.getBalance());
    }
}