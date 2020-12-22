package com.carlosware.kjanitor.configuration;

import static java.util.Objects.requireNonNull;

import com.carlosware.kjanitor.commandline.CommandLine;
import com.carlosware.kjanitor.commandline.CommandLineModes;
import com.carlosware.kjanitor.configuration.cleanup.CleanupConfiguration;
import com.carlosware.kjanitor.configuration.creation.CreationConfiguration;
import com.carlosware.kjanitor.configuration.removal.RemovalConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.Nonnull;

import org.apache.log4j.Logger;

public class ConfigurationReader {
    private static final Logger logger = Logger.getLogger(ConfigurationReader.class);

    private final ObjectMapper objectMapper;
    /**
     * Constructs a new ConfigurationReader
     * @param objectMapper the object mapper to deserialize objects
     */
    public ConfigurationReader(@Nonnull ObjectMapper objectMapper) {
        this.objectMapper = requireNonNull(objectMapper);
    }
    /**
     * Returns a new configuration object
     * @return a new configuration exception
     * @throws ConfigurationReaderException if the configuration could not be built from the given command line
     */
    public Configuration read(@Nonnull CommandLine commandLine) throws ConfigurationReaderException {
        requireNonNull(commandLine);
        try {
            Properties kafkaConfiguration = readKafkaConfiguration(commandLine.getKafkaConfigurationFile());
            CommandLineModes mode = commandLine.getMode();
            String modeConfigurationPath = commandLine.getModeConfigurationFile();
            if (mode == CommandLineModes.CLEANUP) {
                logger.info("Configuring for cleanup");
                CleanupConfiguration cleanupConfiguration = readCleanupConfiguration(modeConfigurationPath);
                return Configuration.from(kafkaConfiguration, cleanupConfiguration);
            } else if (mode == CommandLineModes.CREATION) {
                logger.info("Configuring for topic creation");
                CreationConfiguration creationConfiguration = readCreationConfiguration(modeConfigurationPath);
                return Configuration.from(kafkaConfiguration, creationConfiguration);
            } else if (mode == CommandLineModes.REMOVAL) {
                logger.info("Configuring for removal of topics");
                RemovalConfiguration removalConfiguration = readRemovalConfiguration(modeConfigurationPath);
                return Configuration.from(kafkaConfiguration, removalConfiguration);
            }
            throw new ConfigurationReaderException("Could not create configuration from given command line");
        } catch (IOException ioe) {
            throw new ConfigurationReaderException("Could not read file", ioe);
        }
    }

    private Properties readKafkaConfiguration(@Nonnull String kafkaConfigurationPath) throws IOException {
        Properties kafkaConfiguration = new Properties();
        FileInputStream fileInputStream = new FileInputStream(kafkaConfigurationPath);
        kafkaConfiguration.load(fileInputStream);
        return kafkaConfiguration;
    }

    private CleanupConfiguration readCleanupConfiguration(@Nonnull String cleanupConfigurationPath) throws IOException {
        File cleanupConfigurationFile = new File(requireNonNull(cleanupConfigurationPath));
        return objectMapper.readValue(cleanupConfigurationFile, CleanupConfiguration.class);
    }

    private CreationConfiguration readCreationConfiguration(@Nonnull String creationConfigurationPath) throws IOException {
        File creationConfigurationFile = new File(requireNonNull(creationConfigurationPath));
        return objectMapper.readValue(creationConfigurationFile, CreationConfiguration.class);
    }

    private RemovalConfiguration readRemovalConfiguration(@Nonnull String removalConfigurationPath) throws IOException {
        File removalConfigurationFile = new File(requireNonNull(removalConfigurationPath));
        return objectMapper.readValue(removalConfigurationFile, RemovalConfiguration.class);
    }    
}
