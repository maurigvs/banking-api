package br.maurigvs.banking.transaction.mapper;

import br.maurigvs.banking.account.grpc.TransferReply;
import br.maurigvs.banking.transaction.dto.TransactionRequest;
import br.maurigvs.banking.transaction.dto.TransactionResponse;
import br.maurigvs.banking.transaction.grpc.TransactionReply;
import br.maurigvs.banking.transaction.model.Transaction;
import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.util.List;

public final class TransactionMapper {

    public static Transaction toEntity(Long accountId, TransactionRequest request, Double balance){
        return new Transaction(null,
                accountId,
                request.description(),
                request.amount(),
                balance,
                Instant.now());
    }

    public static List<Transaction> toEntityList(Double amount, TransferReply reply){
        final var timestamp = Instant.now();
        return List.of(
                new Transaction(null,
                        reply.getSender().getId(),
                        "Transfer to Account " + reply.getRecipient().getId(),
                        (amount * -1),
                        reply.getSender().getBalance(),
                        timestamp),
                new Transaction(null,
                        reply.getRecipient().getId(),
                        "Transfer from Account " + reply.getSender().getId(),
                        amount,
                        reply.getRecipient().getBalance(),
                        timestamp)
        );
    }

    public static TransactionResponse toResponse(Transaction transaction){
        return new TransactionResponse(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getBalance(),
                transaction.getTimestamp());
    }

    public static List<TransactionResponse> toResponseList(List<Transaction> transactionList){
        return transactionList.stream()
                .map(TransactionMapper::toResponse)
                .toList();
    }

    public static TransactionReply toReply(Transaction transaction){
        return TransactionReply.newBuilder()
                .setId(transaction.getId())
                .setDescription(transaction.getDescription())
                .setAmount(transaction.getAmount())
                .setBalance(transaction.getBalance())
                .setTimestamp(toTimestamp(transaction.getTimestamp()))
                .build();
    }

    public static Timestamp toTimestamp(Instant instant){
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
