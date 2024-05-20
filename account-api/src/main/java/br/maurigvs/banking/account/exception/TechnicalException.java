package br.maurigvs.banking.account.exception;

public class TechnicalException extends RuntimeException {

    public TechnicalException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }
}
