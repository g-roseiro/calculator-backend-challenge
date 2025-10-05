package com.wit.rest.service;

import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;
import com.wit.rest.kafka.CalculatorRequestProducer;
import com.wit.rest.kafka.ResponseQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CalculatorRestService {

    private static final Logger log = LoggerFactory.getLogger(CalculatorRestService.class);

    private final CalculatorRequestProducer producer;
    private final ResponseQueue responseQueue;

    public CalculatorRestService(CalculatorRequestProducer producer, ResponseQueue responseQueue) {
        this.producer = producer;
        this.responseQueue = responseQueue;
    }

    public CalculatorResponse calculate(CalculatorRequest request) throws InterruptedException {
        log.info("Sending calculation request: operation={}, a={}, b={}", request.getOperation(), request.getA(), request.getB());

        producer.sendRequest(request);
        CalculatorResponse response = responseQueue.waitForResponse(5, TimeUnit.SECONDS);

        if (response == null) {
            log.error("No response received from calculator service within timeout");
        } else {
            log.info("Received response from calculator: result={}, error={}", response.getResult(), response.getError());
        }

        return response;
    }
}
