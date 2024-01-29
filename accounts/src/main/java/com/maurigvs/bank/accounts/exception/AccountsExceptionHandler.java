package com.maurigvs.bank.accounts.exception;

import com.maurigvs.bank.accounts.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountsExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        var errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).sorted().toList();
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }
}