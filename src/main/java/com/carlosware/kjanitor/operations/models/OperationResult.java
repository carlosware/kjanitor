package com.carlosware.kjanitor.operations.models;

import java.util.List;

import javax.annotation.concurrent.Immutable;
import javax.annotation.Nonnull;

import org.immutables.value.Value;

@Immutable
@Value.Immutable
public abstract class OperationResult {
    public static OperationResult from(
        @Nonnull List<String> successes,
        @Nonnull List<String> failures
    ) {
        return ImmutableOperationResult.builder()
            .successes(successes)
            .failures(failures)
            .build();
    }


    public abstract List<String> getSuccesses();

    public abstract List<String> getFailures();

    public Boolean isSuccessful() {
        return getFailures().isEmpty();
    }

    public Boolean isFailure() {
        return getSuccesses().isEmpty();
    }
}
