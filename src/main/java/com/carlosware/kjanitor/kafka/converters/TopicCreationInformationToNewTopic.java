package com.carlosware.kjanitor.kafka.converters;

import java.util.function.Function;

import com.carlosware.kjanitor.kafka.models.TopicCreationInformation;
import org.apache.kafka.clients.admin.NewTopic;

public class TopicCreationInformationToNewTopic implements Function<TopicCreationInformation, NewTopic> {
    @Override
    public NewTopic apply(TopicCreationInformation topicCreationInformation) {
        return new NewTopic(topicCreationInformation.getTopicName(), topicCreationInformation.getPartitionCount(), topicCreationInformation.getReplicationFactor());
    }
}
