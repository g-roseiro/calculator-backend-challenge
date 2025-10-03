package com.wit.calculator.operation.type;

import com.wit.calculator.operation.AbstractOperation;

import java.math.BigDecimal;

public class MultiplicationOperation extends AbstractOperation {
    public MultiplicationOperation(BigDecimal operandA, BigDecimal operandB) {
        super(operandA, operandB);
    }

    @Override
    public BigDecimal solve() {
        return this.operandA.multiply(this.operandB);
    }
}
