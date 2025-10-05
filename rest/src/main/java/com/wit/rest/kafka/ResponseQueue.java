package com.wit.rest.kafka;

import com.wit.common.dto.CalculatorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class ResponseQueue {

    private static final Logger log = LoggerFactory.getLogger(ResponseQueue.class);

    // To be synchronous
    private final BlockingQueue<CalculatorResponse> queue = new LinkedBlockingQueue<>();

    public void addResponse(CalculatorResponse response) {
        log.debug("Adding response to queue (result={}, error={})", response.getResult(), response.getError());
        this.queue.add(response);
    }

    public CalculatorResponse waitForResponse(long timeout, TimeUnit unit) throws InterruptedException {
        log.debug("Waiting for response (timeout={} {})", timeout, unit);
        CalculatorResponse response = queue.poll(timeout, unit);
        if (response == null) {
            log.warn("Response timeout after {} {}", timeout, unit);
        } else {
            log.debug("Response dequeued successfully");
        }
        return response;
    }
}
