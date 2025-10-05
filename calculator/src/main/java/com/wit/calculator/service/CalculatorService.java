package com.wit.calculator.service;

import com.wit.calculator.operation.IOperation;
import com.wit.calculator.operation.OperationFactory;
import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {

    private static final Logger log = LoggerFactory.getLogger(CalculatorService.class);
    private final OperationFactory operationFactory;

    //OperationFactory injected (Component)
    public CalculatorService(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    public CalculatorResponse calculate(CalculatorRequest request) {
        log.info("Performing operation: {} (a={}, b={})", request.getOperation(), request.getA(), request.getB());

        IOperation operation = this.operationFactory.createOperation(request);
        try {
            BigDecimal result = operation.solve();
            log.info("Operation {} completed successfully with result={}", request.getOperation(), result);
            return new CalculatorResponse(result.toPlainString(), null);
        } catch (ArithmeticException e) {
            log.warn("Operation {} failed: {}", request.getOperation(), e.getMessage());
            return new CalculatorResponse(null, e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error while processing {}: {}", request.getOperation(), e.getMessage(), e);
            return new CalculatorResponse(null, "Internal error: " + e.getMessage());
        }
    }
}
