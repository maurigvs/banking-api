package br.maurigvs.banking.customer.exception;

import br.maurigvs.banking.customer.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class RestExceptionHandler {

    private static final String BUSINESS_ERROR = HttpStatus.PRECONDITION_FAILED.getReasonPhrase();
    private static final String DATE_FORMAT_ERROR = "date must have the format: yyyy-MM-dd";
    private static final String INVALID_ARGUMENT_ERROR = HttpStatus.BAD_REQUEST.getReasonPhrase();

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Mono<ErrorResponse> handleBusinessException(BusinessException exception) {
        return Mono.just(new ErrorResponse(BUSINESS_ERROR, exception.getMessage()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> handleDateTimeParseException() {
        return Mono.just(new ErrorResponse(INVALID_ARGUMENT_ERROR, DATE_FORMAT_ERROR));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> handleMethodArgumentNotValid(WebExchangeBindException exception) {
        return Mono.just(new ErrorResponse(INVALID_ARGUMENT_ERROR, buildMessage(exception)));
    }

    private static String[] buildMessage(WebExchangeBindException exception) {
        return exception.getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .toList()
                .toArray(new String[0]);
    }
}
