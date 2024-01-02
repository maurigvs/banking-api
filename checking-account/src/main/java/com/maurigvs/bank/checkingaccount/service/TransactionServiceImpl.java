package com.maurigvs.bank.checkingaccount.service;

import com.maurigvs.bank.checkingaccount.exception.BusinessRuleException;
import com.maurigvs.bank.checkingaccount.model.dto.StatementItemResponse;
import com.maurigvs.bank.checkingaccount.model.entity.Account;
import com.maurigvs.bank.checkingaccount.model.entity.Transaction;
import com.maurigvs.bank.checkingaccount.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public void credit(Account account, String description, Double amount) throws BusinessRuleException {

        if(Double.compare(0.0, amount) > 0)
            throw new BusinessRuleException("Transaction denied");

        repository.save(new Transaction(null, description, amount, account));
    }

    @Override
    public void debit(Account account, String description, Double amount) {
        if(Double.compare(0.0, amount) < 0) amount = amount * -1;
        repository.save(new Transaction(null, description, amount, account));
    }

    //TODO: Implement unit test
    @Override
    public List<StatementItemResponse> parseToStatementItensList(List<Transaction> transactions) {
        return transactions.stream().map(
                transaction -> new StatementItemResponse(
                        transaction.getDateTime().format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
                        transaction.getDescription(),
                        transaction.getAmount())
            ).toList();
    }
}