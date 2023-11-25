package br.com.maurigvs.banking.exception;

import java.time.ZonedDateTime;

public class ErrorMessage {
    
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    private Integer status;
    private String error;
    private String message;

    public ErrorMessage(Integer status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
    public Integer getStatus() {
        return status;
    }
    public String getError() {
        return error;
    }
    public String getMessage() {
        return message;
    }
}
