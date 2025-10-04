package com.wit.calculator.service;

import com.wit.calculator.operation.IOperation;
import com.wit.calculator.operation.OperationFactory;
import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.CalculatorResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {
    private final OperationFactory operationFactory;

    //OperationFactory injected (Component)
    public CalculatorService(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    public CalculatorResponse calculate(CalculatorRequest request) {
        IOperation operation = this.operationFactory.createOperation(request);
        BigDecimal result = operation.solve();
        return new CalculatorResponse(result.toPlainString(), null);
    }
}
