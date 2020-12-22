package com.carlosware.kjanitor.configuration;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.carlosware.kjanitor.configuration.cleanup.CleanupConfiguration;
import com.carlosware.kjanitor.configuration.creation.CreationConfiguration;
import com.carlosware.kjanitor.configuration.creation.TopicCreationConfiguration;
import com.carlosware.kjanitor.configuration.removal.RemovalConfiguration;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.Test;

public abstract class BaseConfigurationDataTest {
    protected Random random = new Random();

    protected int generateRandomInteger() {
        return random.nextInt(100) + 1;
    }

    protected Properties generateKafkaConfiguration() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "kafka-cluster:9092");
        return properties;
    }

    protected List<String> generateStringList(Integer count) {
        return Stream.generate(() -> UUID.randomUUID().toString()).limit(count).collect(toList());
    }

    protected CleanupConfiguration createCleanupConfiguration(List<String> topicsToPreserve) {
        return CleanupConfiguration.from(topicsToPreserve);
    }

    protected RemovalConfiguration createRemovalConfiguration(List<String> topicsToRemove) {
        return RemovalConfiguration.from(topicsToRemove);
    }

    protected TopicCreationConfiguration createTopicCreationConfiguration(String topicName) {
        return TopicCreationConfiguration.from(topicName);
    }

    protected TopicCreationConfiguration createTopicCreationConfiguration(String topicName, Integer partitionCount, Integer replicationFactor) {
        return TopicCreationConfiguration.from(topicName, partitionCount, replicationFactor);
    }

    protected List<TopicCreationConfiguration> createTopicCreationConfigurationList(Integer count) {
        return Stream.generate(() -> createTopicCreationConfiguration(UUID.randomUUID().toString())).limit(count).collect(toList());
    }

    protected CreationConfiguration createCreationConfiguration(Integer defaultPartitionCount, Integer defaultReplicationFactor, List<TopicCreationConfiguration> topics) {
        return CreationConfiguration.from(defaultPartitionCount, defaultReplicationFactor, topics);
    }

    @Test
    public void selftest() {
        assertTrue(true);
    }
}
