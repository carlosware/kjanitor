package com.carlosware.kjanitor.kafka;

import java.util.List;
import javax.annotation.Nonnull;

import com.carlosware.kjanitor.kafka.models.TopicCreationInformation;
import com.carlosware.kjanitor.kafka.models.TopicCreationResults;
import com.carlosware.kjanitor.kafka.models.TopicInformation;

public interface KafkaAdminClient {
    /**
     * Lists the topics in the cluster.
     * @return a list of @see TopicInformation objects
     * @throws KafkaClientException in case of problems
     */
    List<TopicInformation> listTopics() throws KafkaClientException;
    /**
     * Create the requested topics
     * @param toCreate a list of topics to be created, including all the required information to create them
     * @return a list of @see TopicInformation objects
     * @throws KafkaClientException in case of problems
     */
    TopicCreationResults createTopics(@Nonnull List<TopicCreationInformation> toCreate) throws KafkaClientException;
    /**
     * Delete the requested topics
     * @param toDelete a list of topics to be deleted
     * @return a list of @see TopicInformation objects
     * @throws KafkaClientException in case of problems
     */
    List<TopicInformation> deleteTopics(@Nonnull List<String> toDelete) throws KafkaClientException;
}