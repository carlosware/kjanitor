package com.carlosware.kjanitor.configuration.removal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnull;

import java.util.List;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
@JsonDeserialize(as = ImmutableRemovalConfiguration.class)
public abstract class RemovalConfiguration {
    public static RemovalConfiguration from(
        @Nonnull List<String> topics
    ) {
        return ImmutableRemovalConfiguration.builder()
            .topics(topics)
            .build();
    }
    
    public abstract List<String> getTopics();

    @Value.Check
    protected void check() {
        if (getTopics().isEmpty()) {
            throw new IllegalArgumentException("At least one topic must be specified");
        }
    }
}
