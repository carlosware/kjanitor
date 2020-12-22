package com.carlosware.kjanitor.commandline;

import javax.annotation.concurrent.Immutable;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
public abstract class CommandLine {
    public static CommandLine from(
        @Nonnull String kafkaConfigurationFile,
        CommandLineModes mode,
        @Nonnull String modeConfigurationFile
    ) {
        return ImmutableCommandLine.builder()
            .kafkaConfigurationFile(kafkaConfigurationFile)
            .mode(mode)
            .modeConfigurationFile(modeConfigurationFile)
            .build();
    }
    /**
     * The configuration file for Kafka
     * @return the configuration file for Kafka
     */
    public abstract String getKafkaConfigurationFile();
    /**
     * The mode of operation
     * @return the mode of operation specified in the command line
     */
    public abstract CommandLineModes getMode();
    /**
     * The configuration file for the specified mode
     * @return the configuration file for the specified mode
     */
    public abstract String getModeConfigurationFile();
}
