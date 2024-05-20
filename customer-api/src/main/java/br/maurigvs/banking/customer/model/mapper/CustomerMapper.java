package br.maurigvs.banking.customer.model.mapper;

import br.maurigvs.banking.customer.grpc.CustomerReply;
import br.maurigvs.banking.customer.grpc.CustomerRequest;
import br.maurigvs.banking.customer.model.dto.CustomerResponse;
import br.maurigvs.banking.customer.model.entity.Customer;
import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public final class CustomerMapper {

    public static Customer toEntity(CustomerRequest request){
        return new Customer(
                null,
                request.getTaxId(),
                request.getName(),
                request.getSurname(),
                request.getEmailAddress(),
                request.getPhoneNumber(),
                toLocalDate(request.getBirthDate()),
                LocalDate.now());
    }

    public static CustomerResponse toResponse(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getTaxId(),
                customer.getName(),
                customer.getSurname(),
                customer.getEmailAddress(),
                customer.getPhoneNumber(),
                customer.getBirthDate().toString(),
                customer.getJoinDate().toString()
        );
    }

    public static CustomerReply toReply(Customer customer) {
        return CustomerReply.newBuilder()
                .setId(customer.getId())
                .setTaxId(customer.getTaxId())
                .setFullName(customer.getName() + " " + customer.getSurname())
                .setEmailAddress(customer.getEmailAddress())
                .setPhoneNumber(customer.getPhoneNumber())
                .setBirthDate(toTimestamp(customer.getBirthDate()))
                .build();
    }

    private static LocalDate toLocalDate(Timestamp timestamp){
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private static Timestamp toTimestamp(LocalDate localDate){
        var instant = localDate.atStartOfDay(ZoneId.systemDefault());
        return Timestamp.newBuilder()
                .setSeconds(instant.toEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
