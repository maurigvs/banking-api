package br.com.maurigvs.banking.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {
    
    private String taxId;
    private String name;
    private String surname;
}