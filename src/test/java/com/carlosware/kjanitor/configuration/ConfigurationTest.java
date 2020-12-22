package com.carlosware.kjanitor.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.carlosware.kjanitor.configuration.cleanup.CleanupConfiguration;
import com.carlosware.kjanitor.configuration.creation.CreationConfiguration;
import com.carlosware.kjanitor.configuration.creation.TopicCreationConfiguration;
import com.carlosware.kjanitor.configuration.removal.RemovalConfiguration;
import java.util.List;
import java.util.Properties;
import org.junit.Test;

public class ConfigurationTest extends BaseConfigurationDataTest {
    @Test
    public void configurationPropertiesCleanupTest() {
        List<String> topicsToPreserve = generateStringList(generateRandomInteger());
        CleanupConfiguration cleanupConfiguration = createCleanupConfiguration(topicsToPreserve);
        Properties kafkaConfiguration = generateKafkaConfiguration();
        Configuration configuration = Configuration.from(kafkaConfiguration, cleanupConfiguration);

        assertTrue(configuration.getCleanupConfiguration().isPresent());
        assertFalse(configuration.getCreationConfiguration().isPresent());
        assertFalse(configuration.getRemovalConfiguration().isPresent());

        assertEquals(cleanupConfiguration, configuration.getCleanupConfiguration().get());
        assertEquals(kafkaConfiguration, configuration.getKafkaConfiguration());
    }

    @Test(expected = NullPointerException.class)
    public void configurationNullPropertiesCleanupTest() {
        List<String> topicsToPreserve = generateStringList(generateRandomInteger());
        CleanupConfiguration cleanupConfiguration = createCleanupConfiguration(topicsToPreserve);
        Configuration.from(null, cleanupConfiguration);
    }

    @Test(expected = IllegalArgumentException.class)
    public void configurationEmptyPropertiesCleanupTest() {
        List<String> topicsToPreserve = generateStringList(generateRandomInteger());
        CleanupConfiguration cleanupConfiguration = createCleanupConfiguration(topicsToPreserve);
        Configuration.from(new Properties(), cleanupConfiguration);
    }

    @Test(expected = NullPointerException.class)
    public void configurationPropertiesNullCleanupTest() {
        Properties kafkaConfiguration = generateKafkaConfiguration();
        Configuration.from(kafkaConfiguration, (CleanupConfiguration)null);
    }

    @Test
    public void configurationPropertiesRemovalTest() {
        List<String> topicsToRemove = generateStringList(generateRandomInteger());
        RemovalConfiguration removalConfiguration = createRemovalConfiguration(topicsToRemove);
        Properties kafkaConfiguration = generateKafkaConfiguration();
        Configuration configuration = Configuration.from(kafkaConfiguration, removalConfiguration);

        assertFalse(configuration.getCleanupConfiguration().isPresent());
        assertFalse(configuration.getCreationConfiguration().isPresent());
        assertTrue(configuration.getRemovalConfiguration().isPresent());

        assertEquals(removalConfiguration, configuration.getRemovalConfiguration().get());
        assertEquals(kafkaConfiguration, configuration.getKafkaConfiguration());
    }

    @Test(expected = NullPointerException.class)
    public void configurationNullPropertiesRemovalTest() {
        List<String> topicsToRemove = generateStringList(generateRandomInteger());
        RemovalConfiguration removalConfiguration = createRemovalConfiguration(topicsToRemove);
        Configuration.from(null, removalConfiguration);
    }

    @Test(expected = IllegalArgumentException.class)
    public void configurationEmptyPropertiesRemovalTest() {
        List<String> topicsToRemove = generateStringList(generateRandomInteger());
        RemovalConfiguration removalConfiguration = createRemovalConfiguration(topicsToRemove);
        Configuration.from(new Properties(), removalConfiguration);
    }

    @Test(expected = NullPointerException.class)
    public void configurationPropertiesNullRemovalTest() {
        Properties kafkaConfiguration = generateKafkaConfiguration();
        Configuration.from(kafkaConfiguration, (RemovalConfiguration)null);
    }

    @Test
    public void configurationPropertiesCreationTest() {
        int count = generateRandomInteger();
        int defaultPartitionCount = generateRandomInteger();
        int defaultReplicationFactor = generateRandomInteger();
        List<TopicCreationConfiguration> topicCreationConfigurationList = createTopicCreationConfigurationList(count);
        CreationConfiguration creationConfiguration = createCreationConfiguration(defaultPartitionCount, defaultReplicationFactor, topicCreationConfigurationList);
        Properties kafkaConfiguration = generateKafkaConfiguration();
        Configuration configuration = Configuration.from(kafkaConfiguration, creationConfiguration);

        assertFalse(configuration.getCleanupConfiguration().isPresent());
        assertTrue(configuration.getCreationConfiguration().isPresent());
        assertFalse(configuration.getRemovalConfiguration().isPresent());

        assertEquals(creationConfiguration, configuration.getCreationConfiguration().get());
        assertEquals(kafkaConfiguration, configuration.getKafkaConfiguration());
    }

    @Test(expected = NullPointerException.class)
    public void configurationNullPropertiesCreationTest() {
        int count = generateRandomInteger();
        int defaultPartitionCount = generateRandomInteger();
        int defaultReplicationFactor = generateRandomInteger();
        List<TopicCreationConfiguration> topicCreationConfigurationList = createTopicCreationConfigurationList(count);
        CreationConfiguration creationConfiguration = createCreationConfiguration(defaultPartitionCount, defaultReplicationFactor, topicCreationConfigurationList);
        Configuration.from(null, creationConfiguration);
    }

    @Test(expected = IllegalArgumentException.class)
    public void configurationEmptyPropertiesCreationTest() {
        int count = generateRandomInteger();
        int defaultPartitionCount = generateRandomInteger();
        int defaultReplicationFactor = generateRandomInteger();
        List<TopicCreationConfiguration> topicCreationConfigurationList = createTopicCreationConfigurationList(count);
        CreationConfiguration creationConfiguration = createCreationConfiguration(defaultPartitionCount, defaultReplicationFactor, topicCreationConfigurationList);
        Configuration.from(new Properties(), creationConfiguration);
    }

    @Test(expected = NullPointerException.class)
    public void configurationPropertiesNullCreationTest() {
        Properties kafkaConfiguration = generateKafkaConfiguration();
        Configuration.from(kafkaConfiguration, (CreationConfiguration)null);
    }
}
