package br.maurigvs.banking.customer.exception;

public class AlreadyExistsException extends BusinessException {

    public AlreadyExistsException() {
        super("Customer already exists");
    }
}
