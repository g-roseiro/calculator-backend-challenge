package com.wit.calculator.operation;

import java.math.BigDecimal;

public abstract class AbstractOperation implements IOperation {
    protected final BigDecimal operandA;
    protected final BigDecimal operandB;

    public AbstractOperation(BigDecimal operandA, BigDecimal operandB) {
        this.operandA = operandA;
        this.operandB = operandB;
    }
}
