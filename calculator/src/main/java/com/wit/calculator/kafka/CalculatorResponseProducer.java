package com.wit.calculator.kafka;

import com.wit.common.dto.CalculatorResponse;
import com.wit.common.kafka.KafkaTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CalculatorResponseProducer {

    private final KafkaTemplate<String, CalculatorResponse> kafkaTemplate;

    public CalculatorResponseProducer(KafkaTemplate<String, CalculatorResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(CalculatorResponse response) {
        this.kafkaTemplate.send(KafkaTopics.CALCULATOR_RESPONSES, response);
    }
}
