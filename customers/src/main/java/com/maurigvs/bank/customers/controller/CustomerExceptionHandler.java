package com.maurigvs.bank.customers.controller;

import com.maurigvs.bank.customers.model.dto.ApiErrorResponse;
import com.maurigvs.bank.customers.model.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public ApiErrorResponse handleBusinessException(BusinessException ex){
        return new ApiErrorResponse(HttpStatus.PRECONDITION_FAILED.getReasonPhrase(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorResponse handleIllegalArgument(IllegalArgumentException ex){
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        var messages = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .sorted()
                .toList();
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), messages);
    }
}
