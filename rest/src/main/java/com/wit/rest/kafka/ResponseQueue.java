package com.wit.rest.kafka;

import com.wit.common.dto.CalculatorResponse;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class ResponseQueue {
    private final BlockingQueue<CalculatorResponse> queue = new LinkedBlockingQueue<>();

    public void addResponse(CalculatorResponse response) {
        this.queue.add(response);
    }

    public CalculatorResponse waitForResponse(long timeout, TimeUnit unit) throws InterruptedException {
        return queue.poll(timeout, unit);
    }
}
