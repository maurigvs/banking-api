package br.maurigvs.banking.customer.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(Long id) {
        super("Customer not found by id: " + id);
    }
}
