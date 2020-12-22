package com.carlosware.kjanitor.operations;

import com.carlosware.kjanitor.configuration.creation.CreationConfiguration;
import com.carlosware.kjanitor.kafka.KafkaAdminClient;
import com.carlosware.kjanitor.operations.models.OperationResult;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;

import org.apache.log4j.Logger;

public class CreationOperation extends BaseOperation implements Operation {
    private static final Logger logger = Logger.getLogger(CreationOperation.class);
    private final CreationConfiguration configuration;

    public CreationOperation(@Nonnull KafkaAdminClient kafkaClient, @Nonnull CreationConfiguration configuration) {
        super(kafkaClient);
        this.configuration = requireNonNull(configuration);
    }

    @Override
    public OperationResult execute() throws OperationException {
        return null;
    }
}
