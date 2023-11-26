package br.com.maurigvs.banking.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.maurigvs.banking.model.entity.Customer;
import lombok.Getter;

@Getter
public class CustomerResponse {
    
    private final String taxId;

    private final String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate since;

    public CustomerResponse(Customer c){
        this.taxId = c.getTaxId();
        this.name = c.getName() + " " + c.getSurname();
        this.since = c.getSince();
    }
}