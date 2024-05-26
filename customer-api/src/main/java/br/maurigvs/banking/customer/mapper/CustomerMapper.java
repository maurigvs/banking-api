package br.maurigvs.banking.customer.mapper;

import br.maurigvs.banking.customer.grpc.CustomerReply;
import br.maurigvs.banking.customer.grpc.CustomerRequest;
import br.maurigvs.banking.customer.model.Customer;

import java.time.LocalDate;

import static br.maurigvs.banking.customer.mapper.DateTimeMapper.toLocalDate;
import static br.maurigvs.banking.customer.mapper.DateTimeMapper.toTimestamp;

public final class CustomerMapper {

    public static Customer toEntity(CustomerRequest request){
        return new Customer(null,
                request.getTaxId(),
                request.getName(),
                request.getSurname(),
                request.getEmailAddress(),
                request.getPhoneNumber(),
                toLocalDate(request.getBirthDate()),
                LocalDate.now());
    }

    public static CustomerReply toReply(Customer customer){
        return CustomerReply.newBuilder()
                .setId(customer.getId())
                .setTaxId(customer.getTaxId())
                .setFullName(customer.getName() + " " + customer.getSurname())
                .setEmailAddress(customer.getEmailAddress())
                .setPhoneNumber(customer.getPhoneNumber())
                .setBirthDate(toTimestamp(customer.getBirthDate()))
                .setJoinDate(toTimestamp(customer.getJoinDate()))
                .build();
    }
}
