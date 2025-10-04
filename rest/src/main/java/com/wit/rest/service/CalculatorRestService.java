package com.wit.rest.service;

import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;
import com.wit.rest.kafka.CalculatorRequestProducer;
import com.wit.rest.kafka.ResponseQueue;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CalculatorRestService {

    private final CalculatorRequestProducer producer;
    private final ResponseQueue responseQueue;

    public CalculatorRestService(CalculatorRequestProducer producer, ResponseQueue responseQueue) {
        this.producer = producer;
        this.responseQueue = responseQueue;
    }

    public CalculatorResponse calculate(CalculatorRequest request) throws InterruptedException {
        producer.sendRequest(request);
        return responseQueue.waitForResponse(5, TimeUnit.SECONDS);
    }
}
