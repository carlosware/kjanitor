package com.carlosware.kjanitor.configuration.creation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.carlosware.kjanitor.configuration.BaseConfigurationDataTest;
import java.util.UUID;
import org.junit.Test;

public class TopicCreationConfigurationTest extends BaseConfigurationDataTest {
    @Test
    public void topicCreationConfigurationSimpleTest() {
        String topicName = UUID.randomUUID().toString();
        TopicCreationConfiguration topicCreationConfiguration = createTopicCreationConfiguration(topicName);
        assertEquals(topicName, topicCreationConfiguration.getTopicName());
        assertFalse(topicCreationConfiguration.getPartitionCount().isPresent());
        assertFalse(topicCreationConfiguration.getReplicationFactor().isPresent());
    }

    @Test
    public void topicCreationConfigurationCompleteTest() {
        String topicName = UUID.randomUUID().toString();
        int partitionCount = generateRandomInteger();
        int replicationFactor = generateRandomInteger();
        TopicCreationConfiguration topicCreationConfiguration = createTopicCreationConfiguration(topicName, partitionCount, replicationFactor);
        assertEquals(topicName, topicCreationConfiguration.getTopicName());
        assertTrue(topicCreationConfiguration.getPartitionCount().isPresent());
        assertEquals(partitionCount, topicCreationConfiguration.getPartitionCount().getAsInt());
        assertTrue(topicCreationConfiguration.getReplicationFactor().isPresent());
        assertEquals(replicationFactor, topicCreationConfiguration.getReplicationFactor().getAsInt());
    }

    @Test(expected = NullPointerException.class)
    public void topicCreationConfigurationTestNullName() {
        int partitionCount = generateRandomInteger();
        int replicationFactor = generateRandomInteger();
        createTopicCreationConfiguration(null, partitionCount, replicationFactor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void topicCreationConfigurationNegativePartitionCountTest() {
        String topicName = UUID.randomUUID().toString();
        int partitionCount = generateRandomInteger() * -1;
        int replicationFactor = generateRandomInteger();
        createTopicCreationConfiguration(topicName, partitionCount, replicationFactor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void topicCreationConfigurationNegativeReplicationFactorTest() {
        String topicName = UUID.randomUUID().toString();
        int partitionCount = generateRandomInteger();
        int replicationFactor = generateRandomInteger() * -1;
        createTopicCreationConfiguration(topicName, partitionCount, replicationFactor);
    }
}
