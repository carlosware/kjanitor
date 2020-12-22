package com.carlosware.kjanitor.kafka;

public class KafkaClientException extends Exception {
    public KafkaClientException(String message) {
        super(message);
    }
    public KafkaClientException(String message, Throwable cause) {
        super(message, cause);
    }
}