package com.carlosware.kjanitor.kafka.models;

import java.util.List;

import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
public abstract class TopicCreationInformation {
    public static TopicCreationInformation from(
        @Nonnull String topicName,
        @Nonnegative Integer partitionCount,
        @Nonnegative Short replicationFactor
    ) {
        return ImmutableTopicCreationInformation.builder()
            .topicName(topicName)
            .partitionCount(partitionCount)
            .replicationFactor(replicationFactor)
            .build();
    }

    public abstract String getTopicName();

    public abstract Integer getPartitionCount();

    public abstract Short getReplicationFactor();
}
