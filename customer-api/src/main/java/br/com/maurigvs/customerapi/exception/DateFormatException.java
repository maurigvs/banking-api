package br.com.maurigvs.customerapi.exception;

public class DateFormatException extends RuntimeException {

    public DateFormatException(String formatExpected) {
        super("Date must be in the format: " + formatExpected);
    }
}
