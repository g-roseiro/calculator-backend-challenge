package com.wit.calculator.service;

import com.wit.calculator.operation.IOperation;
import com.wit.calculator.operation.OperationFactory;
import com.wit.common.dto.OperationType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorService {
    private final OperationFactory operationFactory;

    //OperationFactory injected (Component)
    public CalculatorService(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    public BigDecimal calculate(OperationType operationType, BigDecimal operandA, BigDecimal operandB) {
        IOperation operation = this.operationFactory.createOperation(operationType, operandA, operandB);
        return operation.solve();
    }
}
