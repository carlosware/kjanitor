package com.carlosware.kjanitor;

public class KJanitorException extends Exception {
    public KJanitorException(String message) {
        super(message);
    }
    public KJanitorException(String message, Throwable cause) {
        super(message, cause);
    }
}
