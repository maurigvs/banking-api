package com.maurigvs.bank.checkingaccount.exception;

import com.maurigvs.bank.checkingaccount.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleAuthenticationException(AuthenticationException ex){
        return new ErrorResponse(HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage());
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidInputException(BusinessRuleException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        var errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).sorted().toList();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }
}