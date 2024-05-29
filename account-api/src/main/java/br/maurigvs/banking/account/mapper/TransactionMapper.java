package br.maurigvs.banking.account.mapper;

import br.maurigvs.banking.account.dto.StatementItemInfo;
import br.maurigvs.banking.transaction.grpc.TransactionReply;
import com.google.protobuf.Timestamp;

import java.time.Instant;

public final class TransactionMapper {

    public static StatementItemInfo toResponse(TransactionReply reply){
        return new StatementItemInfo(
                toInstant(reply.getTimestamp()),
                reply.getDescription(),
                reply.getId(),
                reply.getAmount(),
                reply.getBalance());
    }

    public static Instant toInstant(Timestamp timestamp){
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
    }
}
