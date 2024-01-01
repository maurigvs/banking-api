package com.maurigvs.bank.checkingaccount.exception;

import com.maurigvs.bank.checkingaccount.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LocalExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidInputException(BusinessRuleException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
    }
}