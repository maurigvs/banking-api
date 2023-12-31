package com.maurigvs.bank.accountholder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.maurigvs.bank.accountholder.controller.dto.CreateCompanyRequest;
import com.maurigvs.bank.accountholder.controller.dto.CreatePersonRequest;
import com.maurigvs.bank.accountholder.model.Company;
import com.maurigvs.bank.accountholder.model.Person;

import java.time.LocalDate;

public class Mocks {

    public static CreateCompanyRequest createCompanyRequest() {
        return new CreateCompanyRequest("Company Services",
                "Company Services Ltd.", "03/05/2013", "86580512180",
                "john@wayne.com", "+5511984833929");
    }

    public static CreatePersonRequest createPersonRequest(){
        return new CreatePersonRequest("John", "Wayne",
                "28/07/1988", "86580512180",
                "john@wayne.com", "+5511984833929");
    }

    public static Company company() {
        return new Company(1L, LocalDate.now(), true,
                "Company Services", "Company Services Ltd.",
                LocalDate.of(2013,5,3), "86580512180",
                "john@wayne.com", "+5511984833929");
    }

    public static Person person() {
        return new Person(1L, LocalDate.now(), true,
                "John", "Wayne",
                LocalDate.of(1988,8,28), "86580512180",
                "john@wayne.com", "+5511984833929");
    }

    public static String jsonCompanyRequest() throws Exception {
        return jsonStringFrom(createCompanyRequest());
    }

    public static String jsonPersonRequest() throws Exception {
        return jsonStringFrom(createPersonRequest());
    }

    public static String jsonStringFrom(Object object) throws Exception {
        final var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om.writeValueAsString(object);
    }
}