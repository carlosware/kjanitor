package com.carlosware.kjanitor.operations;

import com.carlosware.kjanitor.configuration.cleanup.CleanupConfiguration;
import com.carlosware.kjanitor.kafka.KafkaAdminClient;
import com.carlosware.kjanitor.operations.models.OperationResult;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;

import org.apache.log4j.Logger;

public class CleanupOperation extends BaseOperation implements Operation {
    private static final Logger logger = Logger.getLogger(CleanupOperation.class);
    private final CleanupConfiguration configuration;

    public CleanupOperation(@Nonnull KafkaAdminClient kafkaClient, @Nonnull CleanupConfiguration configuration) {
        super(kafkaClient);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public OperationResult execute() throws OperationException {
        return null;
    }
}
