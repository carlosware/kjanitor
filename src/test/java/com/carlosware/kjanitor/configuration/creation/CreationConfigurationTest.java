package com.carlosware.kjanitor.configuration.creation;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.carlosware.kjanitor.configuration.BaseConfigurationDataTest;
import java.util.List;
import org.junit.Test;

public class CreationConfigurationTest extends BaseConfigurationDataTest {
    @Test
    public void creationConfigurationSimpleTest() {
        int count = generateRandomInteger();
        int defaultPartitionCount = generateRandomInteger();
        int defaultReplicationFactor = generateRandomInteger();
        List<TopicCreationConfiguration> topicCreationConfigurationList = createTopicCreationConfigurationList(count);
        CreationConfiguration creationConfiguration = createCreationConfiguration(defaultPartitionCount, defaultReplicationFactor, topicCreationConfigurationList);

        int retrievedDefaultPartitionCount = creationConfiguration.getDefaultPartitionCount();
        assertEquals(defaultPartitionCount, retrievedDefaultPartitionCount);
        int retrievedDefaultReplicationFactor = creationConfiguration.getDefaultReplicationFactor();
        assertEquals(defaultReplicationFactor, retrievedDefaultReplicationFactor);
        List<TopicCreationConfiguration> topics = creationConfiguration.getTopics();
        assertEquals(count, topics.size());
        assertEquals(topicCreationConfigurationList, topics);
    }

    @Test(expected = NullPointerException.class)
    public void creationConfigurationNullTopicList() {
        int defaultPartitionCount = generateRandomInteger();
        int defaultReplicationFactor = generateRandomInteger();
        List<TopicCreationConfiguration> topicCreationConfigurationList = null;
        CreationConfiguration creationConfiguration = createCreationConfiguration(defaultPartitionCount, defaultReplicationFactor, topicCreationConfigurationList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creationConfigurationEmptyTopicList() {
        int defaultPartitionCount = generateRandomInteger();
        int defaultReplicationFactor = generateRandomInteger();
        List<TopicCreationConfiguration> topicCreationConfigurationList = emptyList();
        CreationConfiguration creationConfiguration = createCreationConfiguration(defaultPartitionCount, defaultReplicationFactor, topicCreationConfigurationList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creationConfigurationNegativePartitionCountList() {
        int count = generateRandomInteger();
        int defaultPartitionCount = generateRandomInteger() * -1;
        int defaultReplicationFactor = generateRandomInteger();
        List<TopicCreationConfiguration> topicCreationConfigurationList = createTopicCreationConfigurationList(count);
        CreationConfiguration creationConfiguration = createCreationConfiguration(defaultPartitionCount, defaultReplicationFactor, topicCreationConfigurationList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creationConfigurationNegativeReplicationFactorList() {
        int count = generateRandomInteger();
        int defaultPartitionCount = generateRandomInteger();
        int defaultReplicationFactor = generateRandomInteger() * -1;
        List<TopicCreationConfiguration> topicCreationConfigurationList = createTopicCreationConfigurationList(count);
        CreationConfiguration creationConfiguration = createCreationConfiguration(defaultPartitionCount, defaultReplicationFactor, topicCreationConfigurationList);
    }
}
