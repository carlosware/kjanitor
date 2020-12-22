package com.carlosware.kjanitor.operations;

import com.carlosware.kjanitor.operations.models.OperationResult;

/**
 * This interface is used to abstract the different operations supported by KJanitor.
 */
public interface Operation {
    /**
     * Executes the requested operation type.
     * @return OperationResult containing the successes and failures.
     * @throws OperationException in case there was an error, not a failure, processing the requests.
     */
    OperationResult execute() throws OperationException;
}
