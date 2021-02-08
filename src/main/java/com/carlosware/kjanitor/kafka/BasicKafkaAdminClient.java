package com.carlosware.kjanitor.kafka;

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import com.carlosware.kjanitor.kafka.converters.CreateTopicsResultToTopicCreationResults;
import com.carlosware.kjanitor.kafka.converters.TopicCreationInformationToNewTopic;
import com.carlosware.kjanitor.kafka.models.TopicCreationInformation;
import com.carlosware.kjanitor.kafka.models.TopicCreationResults;
import com.carlosware.kjanitor.kafka.models.TopicInformation;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nonnull;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsOptions;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

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
        List<String> topicNames = toCreate.stream().map(t -> t.getTopicName()).collect(toList());
        List<NewTopic> newTopics = toCreate.stream().map(topicCreationInformationToNewTopic).collect(toList());

        CreateTopicsResult createTopicsResult = adminClient.createTopics(newTopics, new CreateTopicsOptions().retryOnQuotaViolation(false));
        Map<String,KafkaFuture<Void>> topicResults = createTopicsResult.values();
        try {
            createTopicsResult.all().get();
            return TopicCreationResults.from(topicNames, emptyList());
        } catch (ExecutionException|InterruptedException ex) {
            List<String> failed = topicNames.stream().filter(n -> topicResults.get(n).isCompletedExceptionally() || topicResults.get(n).isCancelled()).collect(toList());
            List<String> created = topicNames.stream().filter(n -> !topicResults.get(n).isCompletedExceptionally() && !topicResults.get(n).isCancelled()).collect(toList());
            return TopicCreationResults.from(created, failed);
        }
    }

    @Override
    public List<TopicInformation> deleteTopics(@Nonnull List<String> toDelete) throws KafkaClientException {
        return null;
    }

}
