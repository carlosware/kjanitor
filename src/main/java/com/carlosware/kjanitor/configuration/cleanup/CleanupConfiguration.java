package com.carlosware.kjanitor.configuration.cleanup;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
@JsonDeserialize(as = ImmutableCleanupConfiguration.class)
public abstract class CleanupConfiguration {
    public static CleanupConfiguration from(
        @Nonnull List<String> toPreserve
    ) {
        return ImmutableCleanupConfiguration.builder()
            .toPreserve(toPreserve)
            .build();
    }
    public abstract List<String> getToPreserve();
}
