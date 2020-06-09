package com.my.accountmanager.exception;

public class CurrencyServiceException extends RuntimeException {
    public CurrencyServiceException() {
        super();
    }

    public CurrencyServiceException(String message) {
        super(message);
    }

    public CurrencyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyServiceException(Throwable cause) {
        super(cause);
    }

    protected CurrencyServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
