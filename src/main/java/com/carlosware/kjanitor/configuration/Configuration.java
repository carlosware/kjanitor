package com.carlosware.kjanitor.configuration;

import java.util.Optional;
import java.util.Properties;

import javax.annotation.concurrent.Immutable;

import com.carlosware.kjanitor.configuration.cleanup.CleanupConfiguration;
import com.carlosware.kjanitor.configuration.creation.CreationConfiguration;
import com.carlosware.kjanitor.configuration.removal.RemovalConfiguration;

import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
public abstract class Configuration {
    public static Configuration from(
        @Nonnull Properties kafkaConfiguration,
        @Nonnull CreationConfiguration creationConfiguration
    ) {
        return ImmutableConfiguration.builder()
            .kafkaConfiguration(kafkaConfiguration)
            .creationConfiguration(creationConfiguration)
            .build();
    }

    public static Configuration from(
        @Nonnull Properties kafkaConfiguration,
        @Nonnull RemovalConfiguration removalConfiguration
    ) {
        return ImmutableConfiguration.builder()
            .kafkaConfiguration(kafkaConfiguration)
            .removalConfiguration(removalConfiguration)
            .build();
    }

    public static Configuration from(
        @Nonnull Properties kafkaConfiguration,
        @Nonnull CleanupConfiguration cleanupConfiguration
    ) {
        return ImmutableConfiguration.builder()
            .kafkaConfiguration(kafkaConfiguration)
            .cleanupConfiguration(cleanupConfiguration)
            .build();
    }

    public abstract Properties getKafkaConfiguration();

    public abstract Optional<CreationConfiguration> getCreationConfiguration();

    public abstract Optional<RemovalConfiguration> getRemovalConfiguration();

    public abstract Optional<CleanupConfiguration> getCleanupConfiguration();

    @Value.Check
    protected void check() {
        if (!getCreationConfiguration().isPresent() && !getRemovalConfiguration().isPresent() && !getCleanupConfiguration().isPresent()) {
            throw new IllegalArgumentException("At least one operation configuration must be specified");
        }
        if (getKafkaConfiguration().isEmpty()) {
            throw new IllegalArgumentException("The Kafka configuration cannot be empty");
        }
    }
}
