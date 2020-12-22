package com.carlosware.kjanitor.operations;

import com.carlosware.kjanitor.kafka.KafkaAdminClient;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;

public abstract class BaseOperation {
    protected final KafkaAdminClient kafkaClient;

    public BaseOperation(@Nonnull KafkaAdminClient kafkaClient) {
        this.kafkaClient = requireNonNull(kafkaClient);
    }
}
