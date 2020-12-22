package com.carlosware.kjanitor.kafka.converters;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.carlosware.kjanitor.kafka.models.TopicCreationResults;

import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.errors.ApiException; // error returned when creation fails

public class CreateTopicsResultToTopicCreationResults implements Function<CreateTopicsResult, TopicCreationResults> {
    @Override
    public TopicCreationResults apply(CreateTopicsResult createTopicsResult) {
        KafkaFuture<Void> result = createTopicsResult.all();
        return null;
    }
}
