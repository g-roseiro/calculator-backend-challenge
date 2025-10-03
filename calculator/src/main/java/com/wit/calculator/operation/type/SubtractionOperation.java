package com.wit.calculator.operation.type;

import com.wit.calculator.operation.AbstractOperation;

import java.math.BigDecimal;

public class SubtractionOperation extends AbstractOperation {
    public SubtractionOperation(BigDecimal operandA, BigDecimal operandB) {
        super(operandA, operandB);
    }

    @Override
    public BigDecimal solve() {
        return this.operandA.subtract(this.operandB);
    }
}
