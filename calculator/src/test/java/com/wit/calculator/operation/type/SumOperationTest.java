package com.wit.calculator.operation.type;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumOperationTest {

    @Test
    void sumOfTwoPositiveOperands() {
        SumOperation operation = new SumOperation(
                new BigDecimal("3"),
                new BigDecimal("7")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("10"), result);
    }

    @Test
    void sumOfTwoNegativeOperands() {
        SumOperation operation = new SumOperation(
                new BigDecimal("-3"),
                new BigDecimal("-7")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("-10"), result);
    }

    @Test
    void sumOfPositiveAndNegativeOperand() {
        SumOperation operation = new SumOperation(
                new BigDecimal("10"),
                new BigDecimal("-3")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("7"), result);
    }

    @Test
    void sumOfNegativeAndPositiveOperand() {
        SumOperation operation = new SumOperation(
                new BigDecimal("-10"),
                new BigDecimal("3")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("-7"), result);
    }

    @Test
    void sumWithZeroOperand() {
        SumOperation operation = new SumOperation(
                new BigDecimal("8"),
                BigDecimal.ZERO
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("8"), result);
    }

    @Test
    void sumWithDecimalOperands() {
        SumOperation operation = new SumOperation(
                new BigDecimal("1.25"),
                new BigDecimal("2.75")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("4.00"), result.stripTrailingZeros());
    }

    @Test
    void sumWithLargeOperands() {
        SumOperation operation = new SumOperation(
                new BigDecimal("999999999999"),
                new BigDecimal("1")
        );
        BigDecimal result = operation.solve();
        assertEquals(new BigDecimal("1000000000000"), result);
    }

}
