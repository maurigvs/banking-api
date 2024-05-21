package br.maurigvs.banking.transaction.model.mapper;

import br.maurigvs.banking.transaction.grpc.TransactionReply;
import br.maurigvs.banking.transaction.grpc.TransactionRequest;
import br.maurigvs.banking.transaction.model.dto.TransactionResponse;
import br.maurigvs.banking.transaction.model.entity.Transaction;
import com.google.protobuf.Timestamp;

import java.time.Instant;

public final class TransactionMapper {

    public static Transaction toEntity(TransactionRequest request){
        return new Transaction(
                null,
                request.getAccountId(),
                request.getDescription(),
                request.getAmount(),
                Instant.now());
    }

    public static TransactionResponse toResponse(Transaction transaction){
        return new TransactionResponse(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getTimestamp().toString()
        );
    }

    public static TransactionReply toReply(Transaction transaction){
        return TransactionReply.newBuilder()
                .setId(transaction.getId())
                .setDescription(transaction.getDescription())
                .setAmount(transaction.getAmount())
                .setTimestamp(toTimestamp(transaction.getTimestamp()))
                .build();
    }

    private static Timestamp toTimestamp(Instant instant){
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
