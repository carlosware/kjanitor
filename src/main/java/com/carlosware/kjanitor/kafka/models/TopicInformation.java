package com.carlosware.kjanitor.kafka.models;

import java.util.List;

import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
public abstract class TopicInformation {
    public static TopicInformation from(
        @Nonnull String topicName,
        @Nonnegative Integer partitionCount,
        @Nonnegative Integer replicationFactor
    ) {
        return ImmutableTopicInformation.builder()
            .topicName(topicName)
            .partitionCount(partitionCount)
            .replicationFactor(replicationFactor)
            .build();
    }
    public abstract String getTopicName();

    public abstract Integer getPartitionCount();

    public abstract Integer getReplicationFactor();
}
