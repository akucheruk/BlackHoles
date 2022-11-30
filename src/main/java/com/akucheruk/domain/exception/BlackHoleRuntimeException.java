package com.akucheruk.domain.exception;

public class BlackHoleRuntimeException extends RuntimeException {

    public BlackHoleRuntimeException() {
    }

    public BlackHoleRuntimeException(String message) {
        super(message);
    }

    public BlackHoleRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlackHoleRuntimeException(Throwable cause) {
        super(cause);
    }

    public BlackHoleRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
