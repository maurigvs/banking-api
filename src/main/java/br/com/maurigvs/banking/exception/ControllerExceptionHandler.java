package br.com.maurigvs.banking.exception;

import br.com.maurigvs.banking.model.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleBusinessException(BusinessException exception){
        return errorMessage(exception);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    public ErrorMessage handleNoResourceFoundException(NoResourceFoundException exception){
        return errorMessage(exception);
    }

    private ErrorMessage errorMessage(Exception exception){
        return new ErrorMessage(exception.getMessage());
    }
}