package com.carlosware.kjanitor.configuration.creation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
@JsonDeserialize(as = ImmutableCreationConfiguration.class)
public abstract class CreationConfiguration {
    public static CreationConfiguration from(
        @Nonnegative Integer defaultPartitionCount,
        @Nonnegative Integer defaultReplicationFactor,
        @Nonnull List<TopicCreationConfiguration> topics
    ) {
        return ImmutableCreationConfiguration.builder()
            .defaultPartitionCount(defaultPartitionCount)
            .defaultReplicationFactor(defaultReplicationFactor)
            .topics(topics)
            .build();
    }
    public abstract Integer getDefaultPartitionCount();

    public abstract Integer getDefaultReplicationFactor();

    public abstract List<TopicCreationConfiguration> getTopics();

    @Value.Check
    protected void check() {
        if (getDefaultPartitionCount() <= 0) {
            throw new IllegalArgumentException("Default partition count must be larger than zero");
        }
        if (getDefaultReplicationFactor() <= 0) {
            throw new IllegalArgumentException("Default replication factor must be larger than zero");
        }
        if (getTopics().isEmpty()) {
            throw new IllegalArgumentException("At least one topic must be specified");
        }
    }
}
