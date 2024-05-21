package br.maurigvs.banking.transaction.exception;

public class TechnicalException extends RuntimeException {

    public TechnicalException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }
}
