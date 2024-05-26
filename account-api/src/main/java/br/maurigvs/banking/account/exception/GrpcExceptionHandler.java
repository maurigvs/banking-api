package br.maurigvs.banking.account.exception;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

import java.util.List;

public final class GrpcExceptionHandler {

    private static final List<Status.Code> businessStatus = List.of(
            Status.ALREADY_EXISTS.getCode(),
            Status.FAILED_PRECONDITION.getCode());

    public static StatusRuntimeException toServerException(Throwable throwable) {
        if (throwable instanceof BusinessException)
            return Status.FAILED_PRECONDITION
                    .withDescription(throwable.getMessage())
                    .asRuntimeException();

        return Status.INTERNAL.withDescription(throwable.getMessage())
                .asRuntimeException();
    }

    public static RuntimeException toClientException(Throwable throwable) {
        if (throwable instanceof StatusRuntimeException exception) {
            if(businessStatus.contains(exception.getStatus().getCode()))
                return new BusinessException(exception.getStatus().getDescription());
            return new TechnicalException(exception);
        }
        return new RuntimeException(throwable.getMessage());
    }
}
