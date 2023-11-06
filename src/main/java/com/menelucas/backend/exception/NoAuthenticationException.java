package com.menelucas.backend.exception;

public class NoAuthenticationException extends RuntimeException {

    public NoAuthenticationException(String message) {
        super(message);
    }

    public NoAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
