package com.wit.calculator.operation;

import com.wit.calculator.operation.type.DivisionOperation;
import com.wit.calculator.operation.type.MultiplicationOperation;
import com.wit.calculator.operation.type.SubtractionOperation;
import com.wit.calculator.operation.type.SumOperation;
import com.wit.common.dto.OperationType;

import java.math.BigDecimal;

public class OperationFactory {
    public static IOperation createOperation(OperationType operationType, BigDecimal operandA, BigDecimal operandB) {
        return switch (operationType) {
            case SUM -> new SumOperation(operandA, operandB);
            case SUBTRACTION -> new SubtractionOperation(operandA, operandB);
            case MULTIPLICATION -> new MultiplicationOperation(operandA, operandB);
            case DIVISION -> new DivisionOperation(operandA, operandB);
        };
    }
}
