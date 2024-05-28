package br.maurigvs.banking.account.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(Long id) {
        super("Account not found by Id " + id);
    }
}
