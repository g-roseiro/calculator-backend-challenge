package com.wit.calculator.operation.type;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationOperationTest {

    @Test
    void multiplicationOfTwoPositiveOperands() {
        MultiplicationOperation operation = new MultiplicationOperation(
                new BigDecimal("2"),
                new BigDecimal("5")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("10"), result);
    }

    @Test
    void multiplicationOfTwoNegativeOperands() {
        MultiplicationOperation operation = new MultiplicationOperation(
                new BigDecimal("-2"),
                new BigDecimal("-5")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("10"), result);
    }

    @Test
    void multiplicationWithZero() {
        MultiplicationOperation operation = new MultiplicationOperation(
                new BigDecimal("2"),
                BigDecimal.ZERO
        );
        BigDecimal result = operation.solve();
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void multiplicationOfPositiveAndNegativeOperands() {
        MultiplicationOperation operation = new MultiplicationOperation(
                new BigDecimal("3"),
                new BigDecimal("-7")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("-21"), result);
    }

    @Test
    void multiplicationWithDecimalOperands() {
        MultiplicationOperation operation = new MultiplicationOperation(
                new BigDecimal("1.5"),
                new BigDecimal("2.2")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("3.30"), result.stripTrailingZeros());
    }

    @Test
    void multiplicationWithLargeOperands() {
        MultiplicationOperation operation = new MultiplicationOperation(
                new BigDecimal("1000000000000"),
                new BigDecimal("1000000000000")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("1000000000000000000000000"), result);
    }

    @Test
    void multiplicationWithVerySmallOperands() {
        MultiplicationOperation operation = new MultiplicationOperation(
                new BigDecimal("0.00001"),
                new BigDecimal("0.00002")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("0.0000000002"), result.stripTrailingZeros());
    }
}
