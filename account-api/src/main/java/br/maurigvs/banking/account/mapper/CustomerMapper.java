package br.maurigvs.banking.account.mapper;

import br.maurigvs.banking.account.dto.CustomerInfo;
import br.maurigvs.banking.customer.grpc.CustomerRequest;

import static br.maurigvs.banking.account.mapper.DateTimeMapper.toTimestamp;

public final class CustomerMapper {

    public static CustomerRequest toRequest(CustomerInfo request){
        return CustomerRequest.newBuilder()
                .setTaxId(request.taxId())
                .setName(request.name())
                .setSurname(request.surname())
                .setBirthDate(toTimestamp(request.birthDate()))
                .setEmailAddress(request.emailAddress())
                .setPhoneNumber(request.phoneNumber())
                .build();
    }
}
