package br.maurigvs.banking.customer.model.mapper;

import br.maurigvs.banking.customer.model.dto.CustomerRequest;
import br.maurigvs.banking.customer.model.dto.CustomerResponse;
import br.maurigvs.banking.customer.model.entity.Customer;

import java.time.LocalDate;

public final class CustomerMapper {

    public static Customer toEntity(CustomerRequest request){
        return new Customer(
                null,
                request.taxId(),
                request.name(),
                request.surname(),
                request.emailAddress(),
                request.phoneNumber(),
                LocalDate.parse(request.birthDate()),
                LocalDate.now()
        );
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
}
