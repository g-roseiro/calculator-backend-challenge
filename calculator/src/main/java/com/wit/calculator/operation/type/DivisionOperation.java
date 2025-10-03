package com.wit.calculator.operation.type;

import com.wit.calculator.operation.AbstractOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionOperation extends AbstractOperation {

    // Number of decimal fields
    private static final int PRECISION = 50;

    public DivisionOperation(BigDecimal operandA, BigDecimal operandB) {
        super(operandA, operandB);
    }

    @Override
    public BigDecimal solve(){
        if (this.operandB.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by Zero not allowed.");
        }
        return this.operandA.divide(this.operandB, PRECISION, RoundingMode.HALF_UP);
    }
}
