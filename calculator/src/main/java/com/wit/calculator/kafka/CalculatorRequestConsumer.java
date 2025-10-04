package com.wit.calculator.kafka;

import com.wit.calculator.service.CalculatorService;
import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;
import com.wit.common.kafka.KafkaTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorRequestConsumer {

    private final CalculatorService calculatorService;
    private final CalculatorResponseProducer responseProducer;

    public CalculatorRequestConsumer(CalculatorService calculatorService, CalculatorResponseProducer responseProducer) {
        this.calculatorService = calculatorService;
        this.responseProducer = responseProducer;
    }

    @KafkaListener(topics = KafkaTopics.CALCULATOR_REQUESTS, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CalculatorRequest request) {
        CalculatorResponse response = this.calculatorService.calculate(request);
        responseProducer.send(response);
    }
}
