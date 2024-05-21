package br.maurigvs.banking.customer.exception;

public class TechnicalException extends RuntimeException {

    public TechnicalException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }
}
