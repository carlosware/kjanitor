package com.carlosware.kjanitor.operations;

import com.carlosware.kjanitor.configuration.removal.RemovalConfiguration;
import com.carlosware.kjanitor.kafka.KafkaAdminClient;
import com.carlosware.kjanitor.operations.models.OperationResult;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;

import org.apache.log4j.Logger;

public class RemovalOperation extends BaseOperation implements Operation {
    private static final Logger logger = Logger.getLogger(RemovalOperation.class);
    private final RemovalConfiguration configuration;

    public RemovalOperation(@Nonnull KafkaAdminClient kafkaClient, @Nonnull RemovalConfiguration configuration) {
        super(kafkaClient);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public OperationResult execute() throws OperationException {
        return null;
    }
}
