package br.com.maurigvs.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ResponseEntity<ErrorMessage> handleNoResourceFoundException(NoResourceFoundException exception){
        HttpStatus status = HttpStatus.NOT_IMPLEMENTED;
        ErrorMessage error = new ErrorMessage(status.value(), status.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(status).body(error);
    }
}