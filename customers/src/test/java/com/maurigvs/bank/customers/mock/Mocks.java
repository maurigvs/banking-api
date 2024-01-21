package com.maurigvs.bank.customers.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maurigvs.bank.customers.controller.dto.PostCompanyDto;
import com.maurigvs.bank.customers.controller.dto.PostPersonDto;

public class Mocks {

    public static PostCompanyDto ofCreateCompanyRequest() {
        return new PostCompanyDto("Company Services",
                "Company Services Ltd.", "03/05/2013", "72097237000143",
                "john@wayne.com", "+5511984833929");
    }

    public static PostPersonDto ofCreatePersonRequest(){
        return new PostPersonDto("John", "Wayne",
                "28/07/1988", "86580512180",
                "john@wayne.com", "+5511984833929");
    }

    public static String ofJsonFrom(Object object) throws Exception {
        final var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om.writeValueAsString(object);
    }
}