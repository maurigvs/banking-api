package br.maurigvs.banking.account.mapper;

import br.maurigvs.banking.account.dto.AccountRequest;
import br.maurigvs.banking.account.dto.AccountResponse;
import br.maurigvs.banking.account.grpc.TransferReply;
import br.maurigvs.banking.account.grpc.UpdateReply;
import br.maurigvs.banking.account.model.Account;
import reactor.util.function.Tuple2;

import java.time.LocalDate;

public final class AccountMapper {

    public static Account toEntity(AccountRequest request, Long customerId){
        return new Account(null,
                customerId,
                request.initialDeposit(),
                request.pinCode(),
                LocalDate.now());
    }

    public static AccountResponse toResponse(Account account){
        return new AccountResponse(
                account.getId(),
                account.getCustomerId(),
                account.getOpenDate().toString());
    }

    public static UpdateReply toUpdateReply(Account account) {
        return UpdateReply.newBuilder()
                .setId(account.getId())
                .setBalance(account.getBalance())
                .build();
    }

    public static TransferReply toTransferResponse(Tuple2<Account, Account> accountPair){
        return TransferReply.newBuilder()
                .setSender(toUpdateReply(accountPair.getT1()))
                .setRecipient(toUpdateReply(accountPair.getT2()))
                .build();
    }
}
