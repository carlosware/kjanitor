package com.carlosware.kjanitor.configuration;

import com.carlosware.kjanitor.factories.FactoryException;

public class ConfigurationReaderException extends FactoryException {
    public ConfigurationReaderException(String message) {
        super(message);
    }
    public ConfigurationReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
