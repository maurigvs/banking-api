package br.maurigvs.banking.transaction.exception;

import br.maurigvs.banking.transaction.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class RestExceptionHandler {

    private static final String BUSINESS_ERROR = HttpStatus.PRECONDITION_FAILED.getReasonPhrase();
    private static final String INVALID_ARGUMENT_ERROR = HttpStatus.BAD_REQUEST.getReasonPhrase();

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Mono<ErrorResponse> handleBusinessException(BusinessException exception) {
        return Mono.just(new ErrorResponse(BUSINESS_ERROR, exception.getMessage()));
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
