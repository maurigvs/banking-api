package com.maurigvs.bank.customers.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maurigvs.bank.customers.controller.dto.PostCompanyDto;
import com.maurigvs.bank.customers.controller.dto.PostPersonDto;

public class Mocks {

    public static PostCompanyDto ofCreateCompanyRequest() {
        return new PostCompanyDto("72097237000143",
                "Company Services", "Company Services Ltd.",
                "03/05/2013", "john@wayne.com", "+5511984833929");
    }

    public static PostPersonDto ofCreatePersonRequest(){
        return new PostPersonDto("86580512180","John", "Wayne",
                "28/07/1988", "john@wayne.com", "+5511984833929");
    }

    public static String ofJsonFrom(Object object) throws Exception {
        final var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om.writeValueAsString(object);
    }
}