package ru.zderev.sport.exception;

public class AbstractException extends RuntimeException {

    protected AbstractException() {
    }

    protected AbstractException(Throwable cause) {
        super(cause);
    }

    protected AbstractException(String message) {
        super(message);
    }

}
