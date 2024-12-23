package com.jxdev.mytask.Exceptions;

public class EntityExistingException extends RuntimeException{
    public EntityExistingException() {
    }

    public EntityExistingException(String message) {
        super(message);
    }

    public EntityExistingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExistingException(Throwable cause) {
        super(cause);
    }

    public EntityExistingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
