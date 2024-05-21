package br.maurigvs.banking.account.model.mapper;

import br.maurigvs.banking.account.model.dto.AccountRequest;
import br.maurigvs.banking.account.model.dto.TransactionResponse;
import br.maurigvs.banking.account.model.entity.Account;
import br.maurigvs.banking.transaction.grpc.TransactionReply;
import br.maurigvs.banking.transaction.grpc.TransactionRequest;
import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.ZoneId;

public final class TransactionMapper {

    public static final String INITIAL_DEPOSIT = "Initial deposit";

    public static TransactionRequest toRequest(Account account, AccountRequest request){
        return toRequest(account.getId(), INITIAL_DEPOSIT, request.initialDeposit());
    }

    public static TransactionRequest toRequest(br.maurigvs.banking.account.model.dto.TransactionRequest request){
        return toRequest(request.accountId(), request.description(), request.amount());
    }

    public static TransactionResponse toResponse(TransactionReply reply){
        return new TransactionResponse(
                reply.getId(),
                reply.getDescription(),
                reply.getAmount(),
                toString(reply.getTimestamp()));
    }

    private static TransactionRequest toRequest(Long accountId, String description, Double amount){
        return TransactionRequest.newBuilder()
                .setAccountId(accountId)
                .setDescription(description)
                .setAmount(amount)
                .build();
    }

    private static String toString(Timestamp timestamp){
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toString();
    }
}
