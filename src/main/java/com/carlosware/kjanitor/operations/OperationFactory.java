package com.carlosware.kjanitor.operations;

import static java.util.Objects.requireNonNull;

import com.carlosware.kjanitor.commandline.CommandLineModes;
import com.carlosware.kjanitor.configuration.Configuration;
import com.carlosware.kjanitor.configuration.cleanup.CleanupConfiguration;
import com.carlosware.kjanitor.configuration.creation.CreationConfiguration;
import com.carlosware.kjanitor.configuration.removal.RemovalConfiguration;
import com.carlosware.kjanitor.factories.Factory;
import com.carlosware.kjanitor.factories.FactoryException;
import com.carlosware.kjanitor.kafka.KafkaAdminClient;

import javax.annotation.Nonnull;

import org.apache.log4j.Logger;

public class OperationFactory implements Factory<Operation> {
    private static final Logger logger = Logger.getLogger(OperationFactory.class);
    private final CommandLineModes commandLineMode;
    private final Configuration configuration;
    private final KafkaAdminClient kafkaClient;

    public OperationFactory(CommandLineModes commandLineMode, @Nonnull Configuration configuration,
            @Nonnull KafkaAdminClient kafkaClient) {
        this.commandLineMode = commandLineMode;
        this.configuration = requireNonNull(configuration);
        this.kafkaClient = requireNonNull(kafkaClient);
    }

    @Override
    public Operation build() throws FactoryException {
        switch (commandLineMode) {
            case CLEANUP:
                return buildCleanupOperation(kafkaClient, configuration.getCleanupConfiguration().get());
            case CREATION:
                return buildCreationOperation(kafkaClient, configuration.getCreationConfiguration().get());
            case REMOVAL:
                return buildRemovalOperation(kafkaClient, configuration.getRemovalConfiguration().get());
            default:
                throw new FactoryException("Unknown operation");
        }
    }

    private CleanupOperation buildCleanupOperation(KafkaAdminClient kafkaClient, CleanupConfiguration cleanupConfiguration) {
        return new CleanupOperation(kafkaClient, cleanupConfiguration);
    }

    private CreationOperation buildCreationOperation(KafkaAdminClient kafkaClient, CreationConfiguration creationConfiguration) {
        return new CreationOperation(kafkaClient, creationConfiguration);
    }

    private RemovalOperation buildRemovalOperation(KafkaAdminClient kafkaClient, RemovalConfiguration removalConfiguration) {
        return new RemovalOperation(kafkaClient, removalConfiguration);
    }
}
