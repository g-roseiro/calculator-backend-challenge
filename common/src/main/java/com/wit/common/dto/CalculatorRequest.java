package com.wit.common.dto;

import java.math.BigDecimal;

public class CalculatorRequest {
    private Operation operation;
    private BigDecimal a;
    private BigDecimal b;

    public CalculatorRequest(Operation operation, BigDecimal a, BigDecimal b) {
        this.operation = operation;
        this.a = a;
        this.b = b;
    }

    // Getters
    public Operation getOperation() {
        return this.operation;
    }

    public BigDecimal getA() {
        return this.a;
    }

    public BigDecimal getB() {
        return this.b;
    }

    // Setters
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

}
