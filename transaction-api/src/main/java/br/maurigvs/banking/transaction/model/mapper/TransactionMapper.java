package br.maurigvs.banking.transaction.model.mapper;

import br.maurigvs.banking.transaction.model.dto.TransactionRequest;
import br.maurigvs.banking.transaction.model.dto.TransactionResponse;
import br.maurigvs.banking.transaction.model.entity.Transaction;

import java.time.Instant;

public final class TransactionMapper {

    public static Transaction toEntity(TransactionRequest request){
        return new Transaction(
                null,
                request.description(),
                request.amount(),
                Instant.now()
        );
    }

    public static TransactionResponse toResponse(Transaction transaction){
        return new TransactionResponse(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getTimestamp().toString()
        );
    }
}
