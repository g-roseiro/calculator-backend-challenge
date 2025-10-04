package com.wit.calculator.operation;

import com.wit.calculator.operation.type.DivisionOperation;
import com.wit.calculator.operation.type.MultiplicationOperation;
import com.wit.calculator.operation.type.SubtractionOperation;
import com.wit.calculator.operation.type.SumOperation;
import com.wit.common.dto.CalculatorRequest;
import com.wit.common.dto.OperationType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationFactoryTest {

    @Test
    void divisionOperation() {
        CalculatorRequest request = new CalculatorRequest(
                OperationType.DIVISION,
                new BigDecimal("2"),
                new BigDecimal("3")
        );

        OperationFactory factory = new OperationFactory();
        IOperation operation = factory.createOperation(request);

        assertEquals(DivisionOperation.class, operation.getClass());
    }

    @Test
    void multiplicationOperation() {
        CalculatorRequest request = new CalculatorRequest(
                OperationType.MULTIPLICATION,
                new BigDecimal("2"),
                new BigDecimal("3")
        );

        OperationFactory factory = new OperationFactory();
        IOperation operation = factory.createOperation(request);

        assertEquals(MultiplicationOperation.class, operation.getClass());
    }

    @Test
    void sumOperation() {
        CalculatorRequest request = new CalculatorRequest(
                OperationType.SUM,
                new BigDecimal("2"),
                new BigDecimal("3")
        );

        OperationFactory factory = new OperationFactory();
        IOperation operation = factory.createOperation(request);

        assertEquals(SumOperation.class, operation.getClass());
    }

    @Test
    void subtractionOperation() {
        CalculatorRequest request = new CalculatorRequest(
                OperationType.SUBTRACTION,
                new BigDecimal("2"),
                new BigDecimal("3")
        );

        OperationFactory factory = new OperationFactory();
        IOperation operation = factory.createOperation(request);

        assertEquals(SubtractionOperation.class, operation.getClass());
    }
}
