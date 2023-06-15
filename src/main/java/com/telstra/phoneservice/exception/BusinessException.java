package com.telstra.phoneservice.exception;

/**
 * A custom exception to represent the business related exceptions generated with in the
 * phone service
 */
public class BusinessException extends BaseException {

    public BusinessException() {
    }

    /**
     * Accepts the custom message
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Accepts the custom message and {@link Throwable} instance as root cause.
     * @param message
     * @param cause
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
