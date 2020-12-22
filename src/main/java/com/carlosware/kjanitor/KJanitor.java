package com.carlosware.kjanitor;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;

import com.carlosware.kjanitor.operations.Operation;
import com.carlosware.kjanitor.operations.OperationException;
import com.carlosware.kjanitor.operations.models.OperationResult;

import org.apache.log4j.Logger;

public class KJanitor {
    private static final Logger logger = Logger.getLogger(KJanitor.class);
    private final Operation operation;

    public KJanitor(@Nonnull Operation operation) {
        this.operation = requireNonNull(operation);
    }

    public void run() throws KJanitorException {
        logger.info("Performing operation");
        try {
            OperationResult operationResult = operation.execute();
            analyzeResults(operationResult);
        } catch (OperationException oe) {
            throw new KJanitorException("Exception while performing the operation", oe);
        }
    }

    private void analyzeResults(@Nonnull OperationResult operationResult) {
        if (operationResult.isSuccessful()) {
            logger.info("All requests succeeded");
        } else if (operationResult.isFailure()) {
            logger.info("All requests failed");
        } else {
            logger.info("Some requests succeeded and some failed");
            logger.info("Successful requests: " + operationResult.getSuccesses().toString());
            logger.info("Failed requests: " + operationResult.getFailures().toString());
        }
    }
}
