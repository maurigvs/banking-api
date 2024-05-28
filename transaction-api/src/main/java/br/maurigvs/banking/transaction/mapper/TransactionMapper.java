package br.maurigvs.banking.transaction.mapper;

import br.maurigvs.banking.transaction.dto.TransactionRequest;
import br.maurigvs.banking.transaction.dto.TransactionResponse;
import br.maurigvs.banking.transaction.model.Transaction;

import java.time.Instant;

public final class TransactionMapper {

    public static Transaction toEntity(Long accountId, TransactionRequest request, Double balance){
        return new Transaction(null,
                accountId,
                request.description(),
                request.amount(),
                balance,
                Instant.now());
    }

    public static TransactionResponse toResponse(Transaction transaction){
        return new TransactionResponse(
                transaction.getId(),
                transaction.getTimestamp());
    }
}
