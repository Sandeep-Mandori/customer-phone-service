package com.telstra.phoneservice.exception;

/**
 * A custom abstract exception class to represent the hierarchy of exceptions.
 */
public abstract class BaseException extends RuntimeException {

    public BaseException() {
    }

    /**
     * Accepts the custom message
     * @param message
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * Accepts the custom message and {@link Throwable} instance as root cause.
     * @param message
     * @param cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
