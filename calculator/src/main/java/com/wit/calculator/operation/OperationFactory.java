package com.wit.calculator.operation;

import com.wit.calculator.operation.type.DivisionOperation;
import com.wit.calculator.operation.type.MultiplicationOperation;
import com.wit.calculator.operation.type.SubtractionOperation;
import com.wit.calculator.operation.type.SumOperation;
import com.wit.common.dto.CalculatorRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OperationFactory {
    public IOperation createOperation(CalculatorRequest request) {
        return switch (request.getOperation()) {
            case SUM -> new SumOperation(request.getA(), request.getB());
            case SUBTRACTION -> new SubtractionOperation(request.getA(), request.getB());
            case MULTIPLICATION -> new MultiplicationOperation(request.getA(), request.getB());
            case DIVISION -> new DivisionOperation(request.getA(), request.getB());
        };
    }
}