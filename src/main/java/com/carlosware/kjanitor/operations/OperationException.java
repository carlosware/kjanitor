package com.carlosware.kjanitor.operations;

public class OperationException extends Exception{
    public OperationException(String message) {
        super(message);
    }
    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
