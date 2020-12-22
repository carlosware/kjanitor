package com.carlosware.kjanitor.factories;

public class FactoryException extends Exception {
    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
