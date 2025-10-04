package com.wit.common.dto;

import java.math.BigDecimal;

public class CalculatorRequest {
    private OperationType operationType;
    private BigDecimal a;
    private BigDecimal b;

    public CalculatorRequest(OperationType operationType, BigDecimal a, BigDecimal b) {
        this.operationType = operationType;
        this.a = a;
        this.b = b;
    }

    // Beacause of Jackson Deserializationå
    public CalculatorRequest() {
    }

    // Getters
    public OperationType getOperation() {
        return this.operationType;
    }

    public BigDecimal getA() {
        return this.a;
    }

    public BigDecimal getB() {
        return this.b;
    }

    // Setters
    public void setOperation(OperationType operationType) {
        this.operationType = operationType;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

}
