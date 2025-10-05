package com.wit.calculator.kafka;

import com.wit.common.dto.CalculatorResponse;
import com.wit.common.kafka.KafkaTopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CalculatorResponseProducer {

    private static final Logger log = LoggerFactory.getLogger(CalculatorResponseProducer.class);
    private final KafkaTemplate<String, CalculatorResponse> kafkaTemplate;

    public CalculatorResponseProducer(KafkaTemplate<String, CalculatorResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(CalculatorResponse response) {
        log.info("Sending response to topic '{}': result={}, error={}", KafkaTopics.CALCULATOR_RESPONSES, response.getResult(), response.getError());
        this.kafkaTemplate.send(KafkaTopics.CALCULATOR_RESPONSES, response);
    }
}
