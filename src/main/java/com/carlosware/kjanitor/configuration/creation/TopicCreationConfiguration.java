package com.carlosware.kjanitor.configuration.creation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.OptionalInt;

import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
@JsonDeserialize(as = ImmutableTopicCreationConfiguration.class)
public abstract class TopicCreationConfiguration {
    public static TopicCreationConfiguration from(
        @Nonnull String topicName,
        @Nonnegative Integer partitionCount,
        @Nonnegative Integer replicationFactor 
    ) {
        return ImmutableTopicCreationConfiguration.builder()
            .topicName(topicName)
            .partitionCount(partitionCount)
            .replicationFactor(replicationFactor)
            .build();
    }
    public static TopicCreationConfiguration from(
        @Nonnull String topicName
    ) {
        return ImmutableTopicCreationConfiguration.builder()
            .topicName(topicName)
            .build();
    }
    public abstract String getTopicName();
    
    public abstract OptionalInt getPartitionCount();

    public abstract OptionalInt getReplicationFactor();

    @Value.Check
    protected void check() {
        OptionalInt partitionCount = getPartitionCount();
        OptionalInt replicationFactor = getReplicationFactor();
        if (partitionCount.isPresent() && partitionCount.getAsInt() <= 0) {
            throw new IllegalArgumentException("Partition count must larger than zero");
        }
        if (replicationFactor.isPresent() && replicationFactor.getAsInt() <= 0) {
            throw new IllegalArgumentException("Replication factor must be larger than zero");
        }
    }
}
