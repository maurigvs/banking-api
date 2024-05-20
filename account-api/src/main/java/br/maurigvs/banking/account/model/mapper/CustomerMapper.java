package br.maurigvs.banking.account.model.mapper;

import br.maurigvs.banking.customer.grpc.CustomerRequest;
import com.google.protobuf.Timestamp;

import java.time.LocalDate;
import java.time.ZoneId;

public final class CustomerMapper {

    public static CustomerRequest toRequest(br.maurigvs.banking.account.model.dto.CustomerRequest request){
        return CustomerRequest.newBuilder()
                .setTaxId(request.taxId())
                .setName(request.name())
                .setSurname(request.surname())
                .setBirthDate(toTimestamp(request.birthDate()))
                .setEmailAddress(request.emailAddress())
                .setPhoneNumber(request.phoneNumber())
                .build();
    }

    private static Timestamp toTimestamp(String date){
        var instant = LocalDate.parse(date)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
