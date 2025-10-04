package com.wit.calculator.operation.type;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivisionOperationTest {

    @Test
    void testDivisionOfTwoPositiveOperands() {
        DivisionOperation operation = new DivisionOperation(
                new BigDecimal("10"),
                new BigDecimal("2")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("5.00000000000000000000000000000000000000000000000000"), result);
    }

    @Test
    void testDivisionWithDecimalResult() {
        DivisionOperation operation = new DivisionOperation(
                new BigDecimal("1"),
                new BigDecimal("3")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("0.33333333333333333333333333333333333333333333333333"),result);
    }

    @Test
    void testDivisioWithNegativeNumber() {
        DivisionOperation operation = new DivisionOperation(
                new BigDecimal("10"),
                new BigDecimal("-2")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("-5.00000000000000000000000000000000000000000000000000"), result);
    }

    @Test
    void testDivisionByZeroException() {
        DivisionOperation operation = new DivisionOperation(
                new BigDecimal("1"),
                BigDecimal.ZERO
        );
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                operation::solve
        );
        assertEquals("Division by Zero not allowed.", exception.getMessage());
    }
}
