package com.swivel.util.exception;

/**
 * OtpCache Exception
 */
public class UtilServiceException extends RuntimeException {
    /**
     * OtpCache Exception with error message
     *
     * @param errorMessage error message
     */
    public UtilServiceException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * OtpCache Exception with error message and throwable
     *
     * @param errorMessage error message
     * @param throwable    error
     */
    public UtilServiceException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
