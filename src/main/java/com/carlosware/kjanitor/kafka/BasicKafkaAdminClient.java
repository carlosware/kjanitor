package com.carlosware.kjanitor.kafka;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import com.carlosware.kjanitor.kafka.converters.CreateTopicsResultToTopicCreationResults;
import com.carlosware.kjanitor.kafka.converters.TopicCreationInformationToNewTopic;
import com.carlosware.kjanitor.kafka.models.TopicCreationInformation;
import com.carlosware.kjanitor.kafka.models.TopicCreationResults;
import com.carlosware.kjanitor.kafka.models.TopicInformation;

import java.util.List;
import java.util.Properties;

import javax.annotation.Nonnull;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class BasicKafkaAdminClient implements KafkaAdminClient {
    private final AdminClient adminClient;

    public BasicKafkaAdminClient(@Nonnull Properties kafkaConfiguration) {
        this.adminClient = AdminClient.create(requireNonNull(kafkaConfiguration));
    }

    @Override
    public List<TopicInformation> listTopics() throws KafkaClientException {
        return null;
    }
    @Override
    public TopicCreationResults createTopics(@Nonnull List<TopicCreationInformation> toCreate) throws KafkaClientException {
        TopicCreationInformationToNewTopic topicCreationInformationToNewTopic = new TopicCreationInformationToNewTopic();
        CreateTopicsResultToTopicCreationResults createTopicsResultToTopicCreationResults = new CreateTopicsResultToTopicCreationResults();
        List<NewTopic> newTopics = toCreate.stream().map(topicCreationInformationToNewTopic).collect(toList());
        CreateTopicsResult createTopicsResult = adminClient.createTopics(newTopics, null);
        return createTopicsResultToTopicCreationResults.apply(createTopicsResult);
    }

    @Override
    public List<TopicInformation> deleteTopics(@Nonnull List<String> toDelete) throws KafkaClientException {
        return null;
    }

}
