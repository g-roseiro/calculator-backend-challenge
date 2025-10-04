package com.wit.rest.kafka;

import com.wit.common.dto.CalculatorRequest;
import com.wit.common.kafka.KafkaTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CalculatorRequestProducer {

    private final KafkaTemplate<String, CalculatorRequest> kafkaTemplate;

    public CalculatorRequestProducer(KafkaTemplate<String, CalculatorRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRequest(CalculatorRequest request) {
        kafkaTemplate.send(KafkaTopics.CALCULATOR_REQUESTS, request);
    }

}
