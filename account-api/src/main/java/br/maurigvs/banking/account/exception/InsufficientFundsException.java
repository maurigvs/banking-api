package br.maurigvs.banking.account.exception;

public class InsufficientFundsException extends BusinessException {

    public InsufficientFundsException() {
        super("Insufficient funds for this operation.");
    }
}
