package com.carlosware.kjanitor.operations;

import com.carlosware.kjanitor.factories.FactoryException;

public class OperationFactoryException extends FactoryException {
    public OperationFactoryException(String message) {
        super(message);
    }
    public OperationFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
