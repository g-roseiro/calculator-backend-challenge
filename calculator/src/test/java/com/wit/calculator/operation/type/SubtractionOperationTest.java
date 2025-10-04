package com.wit.calculator.operation.type;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionOperationTest {
    @Test
    void subtractionOfTwoPositiveOperands() {
        SubtractionOperation operation = new SubtractionOperation(
                new BigDecimal("8"),
                new BigDecimal("3")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("5"), result);
    }

    @Test
    void subtractionOfTwoNegativeOperands() {
        SubtractionOperation operation = new SubtractionOperation(
                new BigDecimal("-8"),
                new BigDecimal("-3")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("-5"), result);
    }

    @Test
    void subtractionOfPositiveAndNegativeOperand() {
        SubtractionOperation operation = new SubtractionOperation(
                new BigDecimal("8"),
                new BigDecimal("-3")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("11"), result);
    }

    @Test
    void subtractionOfNegativeAndPositiveOperand() {
        SubtractionOperation operation = new SubtractionOperation(
                new BigDecimal("-8"),
                new BigDecimal("3")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("-11"), result);
    }

    @Test
    void subtractionResultingInZero() {
        SubtractionOperation operation = new SubtractionOperation(
                new BigDecimal("5"),
                new BigDecimal("5")
        );
        BigDecimal result = operation.solve();
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void subtractionWithDecimalOperands() {
        SubtractionOperation operation = new SubtractionOperation(
                new BigDecimal("5.75"),
                new BigDecimal("2.25")
        );
        BigDecimal result = operation.solve();
        assertEquals(0, result.compareTo(new BigDecimal("3.50")));
    }

    @Test
    void subtractionWithLargeOperands() {
        SubtractionOperation operation = new SubtractionOperation(
                new BigDecimal("1000000000000"),
                new BigDecimal("999999999999")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("1"), result);
    }
}
