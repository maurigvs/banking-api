package br.maurigvs.banking.account.exception;

public class InsufficientBalanceException extends BusinessException {

    public InsufficientBalanceException() {
        super("Account balance is insufficient");
    }
}
