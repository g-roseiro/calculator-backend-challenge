package com.wit.rest.kafka;

import com.wit.common.dto.CalculatorResponse;
import com.wit.common.kafka.KafkaTopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorResponseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorResponseConsumer.class);
    private final ResponseQueue responseQueue;

    public CalculatorResponseConsumer(ResponseQueue responseQueue) {
        this.responseQueue = responseQueue;
    }

    @KafkaListener(topics = KafkaTopics.CALCULATOR_RESPONSES, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CalculatorResponse response) {
        logger.info("Received CalculatorResponse from Kafka: result={}, error={}", response.getResult(), response.getError());
        this.responseQueue.addResponse(response);
    }
}
