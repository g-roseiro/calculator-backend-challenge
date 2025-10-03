package com.wit.calculator.operation.type;

import com.wit.calculator.operation.AbstractOperation;

import java.math.BigDecimal;

public class SumOperation extends AbstractOperation {
    public SumOperation(BigDecimal operandA, BigDecimal operandB) {
        super(operandA, operandB);
    }

    @Override
    public BigDecimal solve() {
        return this.operandA.add(this.operandB);
    }
}
