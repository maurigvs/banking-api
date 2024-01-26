package com.maurigvs.bank.customers.exception;

import com.maurigvs.bank.customers.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomersExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleInvalidInputException(BusinessException ex){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        var errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).sorted().toList();
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }
}