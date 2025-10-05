package com.wit.calculator.kafka;

import com.wit.calculator.service.CalculatorService;
import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;
import com.wit.common.kafka.KafkaTopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorRequestConsumer {

    private static final Logger log = LoggerFactory.getLogger(CalculatorRequestConsumer.class);

    private final CalculatorService calculatorService;
    private final CalculatorResponseProducer responseProducer;

    public CalculatorRequestConsumer(CalculatorService calculatorService, CalculatorResponseProducer responseProducer) {
        this.calculatorService = calculatorService;
        this.responseProducer = responseProducer;
    }

    @KafkaListener(topics = KafkaTopics.CALCULATOR_REQUESTS, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CalculatorRequest request) {

        // get requestId from payload to put in MDC
        String requestId = request.getRequestId();
        if (requestId != null && !requestId.isBlank()) {
            MDC.put("requestId", requestId);
        }

        try {
            log.info("Received CalculatorRequest: operation={}, a={}, b={}", request.getOperation(), request.getA(), request.getB());
            CalculatorResponse response = this.calculatorService.calculate(request);
            responseProducer.send(response);
            log.debug("Response sent to Kafka topic '{}': result={}, error={}", KafkaTopics.CALCULATOR_RESPONSES, response.getResult(), response.getError());
        } finally {
            MDC.clear();
        }

    }
}
