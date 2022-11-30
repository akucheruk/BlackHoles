package com.akucheruk.domain.exception;

public class InvalidInputArgument extends RuntimeException {

    public InvalidInputArgument() {
    }

    public InvalidInputArgument(String message) {
        super(message);
    }

    public InvalidInputArgument(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputArgument(Throwable cause) {
        super(cause);
    }

    public InvalidInputArgument(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
