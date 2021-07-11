package com.example.demo.api.handler;

import org.slf4j.Logger;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        super();
    }
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message, Logger LOG) {
        super(message);
        LOG.error(message);
    }
    public ValidationException(String message) {
        super(message);

    }
    public ValidationException(Throwable cause) {
        super(cause);
    }
}