package br.com.maurigvs.banking.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {
    
    private String taxId;
    private String name;
    private String surname;
    private Double initialDeposit;
}