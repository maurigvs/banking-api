package br.maurigvs.banking.customer.exception;

public class AgeNotEligibleException extends BusinessException {

    public AgeNotEligibleException() {
        super("Customer must be at least 18 years old");
    }
}
