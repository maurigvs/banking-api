package br.maurigvs.banking.transaction.exception;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public final class GrpcExceptionHandler {

    public static StatusRuntimeException toServerException(Throwable throwable) {
        if (throwable instanceof BusinessException)
            return Status.FAILED_PRECONDITION
                    .withDescription(throwable.getMessage())
                    .asRuntimeException();

        return Status.INTERNAL.withDescription(throwable.getMessage())
                .asRuntimeException();
    }
}
