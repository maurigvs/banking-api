package br.com.maurigvs.customerapi.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ContactInfo implements Serializable {

    private String emailAddress;
    private String phoneNumber;

    public ContactInfo(String emailAddress, String phoneNumber) {
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    protected ContactInfo() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
