package com.carlosware.kjanitor.kafka.models;

import java.util.List;

import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
public abstract class TopicCreationResults {
    public static TopicCreationResults from(
        @Nonnull List<String> created,
        @Nonnull List <String> failedToCreate
    ) {
        return ImmutableTopicCreationResults.builder()
            .created(created)
            .failedToCreate(failedToCreate)
            .build();
    }


    public abstract List<String> getCreated();

    public abstract List<String> getFailedToCreate();
}
