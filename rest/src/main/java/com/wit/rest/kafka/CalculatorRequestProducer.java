package com.wit.rest.kafka;

import com.wit.common.dto.CalculatorRequest;
import com.wit.common.kafka.KafkaTopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CalculatorRequestProducer {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorRequestProducer.class);
    private final KafkaTemplate<String, CalculatorRequest> kafkaTemplate;

    public CalculatorRequestProducer(KafkaTemplate<String, CalculatorRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRequest(CalculatorRequest request) {
        logger.info("Sending CalculatorRequest to topic '{}'.", KafkaTopics.CALCULATOR_REQUESTS);

        try {
            kafkaTemplate.send(KafkaTopics.CALCULATOR_REQUESTS, request);
            logger.debug("Kafka send() executed successfully");
        } catch (Exception e) {
            logger.error("Failed to send request to Kafka: {}", e.getMessage());
        }

    }

}
