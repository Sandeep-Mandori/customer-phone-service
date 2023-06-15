package com.telstra.phoneservice.exception;

/**
 * A custom exception to represent the system related exceptions generated with in the
 * phone service
 */
public class SystemException extends BaseException {

    public SystemException() {
    }

    /**
     * Accepts the custom message
     * @param message
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * Accepts the custom message and {@link Throwable} instance as root cause.
     * @param message
     * @param cause
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
