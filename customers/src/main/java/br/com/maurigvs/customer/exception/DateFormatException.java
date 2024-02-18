package br.com.maurigvs.customer.exception;

public class DateFormatException extends RuntimeException {

    public DateFormatException(String formatExpected) {
        super("birthDate must be in the format: " + formatExpected);
    }
}
